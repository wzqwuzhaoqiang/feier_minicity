package com.feier.userServer.service;

import com.feier.userServer.entity.UserPassword;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
public interface UserPasswordService extends IService<UserPassword> {

    UserPassword getPasswordByUserId(Integer id);
}
