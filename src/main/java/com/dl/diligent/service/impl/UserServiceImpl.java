package com.dl.diligent.service.impl;

import com.dl.diligent.config.wechat.handler.ServiceException;
import com.dl.diligent.dto.DiUserResult;
import com.dl.diligent.dto.UserParam;
import com.dl.diligent.entity.DiUser;
import com.dl.diligent.enums.ServiceExceptionEnum;
import com.dl.diligent.mapper.DiUserMapper;
import com.dl.diligent.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dail
 * @description
 * @since 2018/11/19 10:20
 */
@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @Resource
    private DiUserMapper diUserMapper;


    /**
     * 创建用户
     *
     * @param  userEntity 用户实体
     * @return java.lang.Long 用户id
     * @author dail
     * @since  2018/11/22 15:25
     */
    @Override
    public Long createUser(DiUser userEntity, Long fromId) {

         if(StringUtils.isEmpty(userEntity.getOpenId())){
             log.error("create user error,openId is null");
            throw new ServiceException(ServiceExceptionEnum.PARAM_ERROR);
         }
         UserParam param = new UserParam();
         param.setOpenId(userEntity.getOpenId());
         DiUserResult user = getUser(param);
         if(!StringUtils.isEmpty(user)){
             userEntity.setId(user.getId());
             diUserMapper.updateByPrimaryKeySelective(userEntity);
             log.info("update user, id{}, openId {}, nickname {}",userEntity.getId(), userEntity.getOpenId(),userEntity.getNickName());
             return user.getId();
         }
        log.info("create user, openId {}, nickname {}",userEntity.getOpenId(),userEntity.getNickName());
        diUserMapper.insertSelective(userEntity);
        Long userId = userEntity.getId();

        return userId;
    }

    /**
     * 根据条件查询用户
     *
     * @param  param  nullable(id/openId/nickName)
     * @return UserResult
     * @author dail
     * @since  2018/12/4 10:13
     */
    @Override
    public DiUserResult getUser(UserParam param) {
        List<DiUserResult> userResults = diUserMapper.selectByExample(param);
        if(CollectionUtils.isEmpty(userResults)){
            return  new DiUserResult();
        }
        return userResults.get(0);
    }
}
