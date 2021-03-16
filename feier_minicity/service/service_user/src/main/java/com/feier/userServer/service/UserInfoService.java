package com.feier.userServer.service;

import com.feier.userServer.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feier.userServer.service.model.UserModel;
import com.feier.utilServer.error.BusinessException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
public interface UserInfoService extends IService<UserInfo> {

    UserModel selectUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
    UserModel validataLogin(String telphone,String password) throws BusinessException;
}
