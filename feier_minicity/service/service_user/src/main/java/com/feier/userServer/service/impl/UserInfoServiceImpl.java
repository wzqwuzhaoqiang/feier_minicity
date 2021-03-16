package com.feier.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feier.userServer.entity.UserInfo;
import com.feier.userServer.entity.UserPassword;
import com.feier.userServer.mapper.UserInfoMapper;
import com.feier.userServer.mapper.UserPasswordMapper;
import com.feier.userServer.service.UserInfoService;
import com.feier.userServer.service.UserPasswordService;
import com.feier.userServer.service.model.UserModel;
import com.feier.utilServer.error.BusinessException;
import com.feier.utilServer.error.EmBusinessError;
import com.feier.utilServer.validator.ValidationResult;
import com.feier.utilServer.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private UserPasswordService passwordService;

    @Autowired
    private UserPasswordMapper passwordMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel selectUserById(Integer id) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        if (userInfo==null){
            return null;
        }
        UserPassword userPassword = passwordService.getPasswordByUserId(userInfo.getId());

        return convertFromDataObject(userInfo,userPassword);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserModel userModel) throws BusinessException {
        if (userModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
//        if (StringUtils.isEmpty(userModel.getName())
//        ||userModel.getGender()==null
//        ||userModel.getAge()==null
//        ||StringUtils.isEmpty(userModel.getTelphone())
//        ||StringUtils.isEmpty(userModel.getEncrptPassword())){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }

        ValidationResult val = validator.validate(userModel);
        if (val.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,val.getErrMsg());
        }

        //实现 UserModel--> userInfo
        UserInfo userInfo = convertFromModel(userModel);
        //userInfoMapper.insertSelect(userInfo);
        userInfoMapper.insert(userInfo);
        UserPassword userPassword = convertPasswordFromModel(userModel,userInfo.getId());
        //passwordMapper.insertSelect(userPassword);
       // passwordService.sa
        passwordMapper.insert(userPassword);
    }

    @Override
    public UserModel validataLogin(String telphone, String password) throws BusinessException {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("telphone", telphone);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (userInfo==null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        QueryWrapper<UserPassword> pwrapper = new QueryWrapper<>();
        pwrapper.eq("user_id", userInfo.getId());
        UserPassword userPassword = passwordMapper.selectOne(pwrapper);
        if (userPassword==null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserModel userModel = convertFromDataObject(userInfo, userPassword);
        if (!userModel.getEncrptPassword().equals(password)){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }



    private UserPassword convertPasswordFromModel(UserModel userModel,Integer userId){
        if (userModel==null){
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncrptPassword());
        userPassword.setUserId(userId);
        //System.out.println("userid："+userModel.getId());
        return userPassword;
    }

    private UserInfo convertFromModel(UserModel userModel){
        if (userModel==null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel, userInfo);
        return userInfo;
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassword userPassword){

        if (userInfo==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo, userModel);
        if (userPassword!=null){
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }
}
