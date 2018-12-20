package com.dl.diligent.service;


import com.dl.diligent.dto.DiUserResult;
import com.dl.diligent.dto.UserParam;
import com.dl.diligent.dto.UserResult;
import com.dl.diligent.entity.DiUser;

/**
 * @author dail
 * @description
 * @since 2018/11/19 10:20
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param  userEntity 用户实体
     * @return java.lang.Long 用户id
     * @author dail
     * @since  2018/11/22 15:25
     */
    Long createUser(DiUser userEntity, Long fromId);

    /**
     * 根据条件查询用户
     *
     * @param  param  nullable(id/openId/nickName)
     * @return UserResult
     * @author dail
     * @since  2018/12/4 10:13
     */
    DiUserResult getUser(UserParam param);
}
