package com.feier.userServer.mapper;

import com.feier.userServer.entity.UserPassword;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
public interface UserPasswordMapper extends BaseMapper<UserPassword> {

    void insertSelect(UserPassword userPassword);
}
