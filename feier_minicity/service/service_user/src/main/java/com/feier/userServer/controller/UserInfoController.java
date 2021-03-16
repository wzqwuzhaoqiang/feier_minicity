package com.feier.userServer.controller;


import com.feier.userServer.controller.vo.UserVo;
import com.feier.userServer.service.UserInfoService;
import com.feier.userServer.service.model.UserModel;
import com.feier.utilServer.MD5;
import com.feier.utilServer.RandomUtil;
import com.feier.utilServer.controllerErrorParent.BaseController;
import com.feier.utilServer.error.BusinessException;
import com.feier.utilServer.error.EmBusinessError;
import com.feier.utilServer.response.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/userServer/userInfo")
@Api(description = "用户模块")  //@ApiOperation(value = "添加商品")
@CrossOrigin
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation(value = "用户登入")
    @PostMapping("login")
    public CommonReturnType login(@RequestParam("telphone")String telphone,
                                  @RequestParam("password")String password) throws BusinessException {

        if (org.apache.commons.lang3.StringUtils.isEmpty(telphone)
        ||org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserModel userModel = userInfoService.validataLogin(telphone, MD5.encrypt(password));

        //TODO  用userModel信息生成token，实现分布式的登入

        return CommonReturnType.create(null);
    }


    @ApiOperation(value = "根据ID获取对应用户信息")
    @GetMapping("getUserById/{id}")
    public CommonReturnType getUserById(@PathVariable("id") Integer id){

        UserModel userModel = userInfoService.selectUserById(id);
        UserVo userVo = convertFromModel(userModel);
        return CommonReturnType.create(userVo);

    }

    @ApiOperation(value = "用户获取otp短信接口")
    @PostMapping("getOtp")
    public CommonReturnType getOtp(@RequestParam("telphone") String telphone){

        //从redis中获取验证码，取到直接返回
        String code = redisTemplate.opsForValue().get(telphone);
        if (!StringUtils.isEmpty(code)){
            return CommonReturnType.create(code);
        }
        //按规则生成otp 验证码
        String fourBitRandom = RandomUtil.getFourBitRandom();
        //将验证码与手机号关联
        redisTemplate.opsForValue().set(telphone,fourBitRandom,1, TimeUnit.MINUTES);
        //将otp验证码通过短信发送给用户
        return CommonReturnType.create(fourBitRandom);

    }


    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public CommonReturnType register(@RequestParam("telphone") String telphone,
                                     @RequestParam("otpCode") String otpCode,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") Integer gender,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("password") String password) throws BusinessException {
        //验证手机号和对应的otpCode是否相等
        Map<String,Object> map = new HashMap<>();
        String redisOtpCode = redisTemplate.opsForValue().get(telphone);
        if (StringUtils.isEmpty(redisOtpCode)){
            throw new BusinessException(EmBusinessError.EXPIER_OTP_CODE);
        }
        if (!redisOtpCode.equals(otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"验证码错误");
        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setAge(age);
        userModel.setGender(gender);
        userModel.setName(name);
        userModel.setTelphone(telphone);
        userModel.setEncrptPassword(MD5.encrypt(password));
        userInfoService.register(userModel);
        map.put("result", "用户创建成功");
        return CommonReturnType.create(map);
    }












    private UserVo convertFromModel(UserModel userModel){
        UserVo userVo = new UserVo();
        if (userModel==null){
            return null;
        }
        BeanUtils.copyProperties(userModel, userVo);
        return userVo;
    }

}

