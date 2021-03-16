package com.feier.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feier.userServer.entity.UserPassword;
import com.feier.userServer.mapper.UserPasswordMapper;
import com.feier.userServer.service.UserPasswordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
@Service
public class UserPasswordServiceImpl extends ServiceImpl<UserPasswordMapper, UserPassword> implements UserPasswordService {

    @Autowired
    private UserPasswordMapper passwordMapper;

    @Override
    public UserPassword getPasswordByUserId(Integer id) {

        QueryWrapper<UserPassword> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        UserPassword userPassword = passwordMapper.selectOne(wrapper);
        return userPassword;
    }
}
