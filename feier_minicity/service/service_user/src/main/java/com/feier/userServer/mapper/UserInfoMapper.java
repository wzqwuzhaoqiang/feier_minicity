package com.feier.userServer.mapper;

import com.feier.userServer.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    void insertSelect(UserInfo userInfo);

    // UserInfo selectUserById();
}
