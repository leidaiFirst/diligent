package com.dl.diligent.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.dl.diligent.config.wechat.handler.ServiceException;
import com.dl.diligent.consts.CommonConst;
import com.dl.diligent.dto.DiUserResult;
import com.dl.diligent.dto.MiniAppLoginParam;
import com.dl.diligent.dto.UserParam;
import com.dl.diligent.dto.UserTokenResult;
import com.dl.diligent.entity.DiUser;
import com.dl.diligent.enums.IsDeleteEnum;
import com.dl.diligent.enums.ServiceExceptionEnum;
import com.dl.diligent.helper.TokenHelper;
import com.dl.diligent.service.LoginService;
import com.dl.diligent.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author dail
 * @description
 * @since 2018/11/19 10:20
 */
@Service
@Slf4j
public class LoginServiceImpl  implements LoginService {
    @Resource
    private WxMaService wxMaService;

    @Resource
    private UserService  userService;


    private static final String WX_FROM = "miniapp";


    @Override
    public UserTokenResult login(MiniAppLoginParam loginParam) throws Exception {

        //1.获取微信用户信息
        WxMaUserInfo wxMaUserInfo = getWxAppUserInfo(loginParam);
        log.info("user login, openId {}, nickname {}", wxMaUserInfo.getOpenId(),wxMaUserInfo.getNickName());
        //2.创建用户
        Long userId = createUserInfo(wxMaUserInfo,loginParam.getFromId());
        //3.生成token
        String token = TokenHelper.token(userId,WX_FROM);

        UserTokenResult userTokenResult = new UserTokenResult();
        userTokenResult.setAuthCode(token);
        userTokenResult.setUserId(userId);

        return userTokenResult;
    }

    @Override
    public String refresh(MiniAppLoginParam miniAppLoginParam) throws WxErrorException {
        UserParam param = new UserParam();
        param.setOpenId(getWxAppUserInfo(miniAppLoginParam).getOpenId());
        DiUserResult user = userService.getUser(param);
        if (Objects.isNull(user)) {
            log.error("user not exits,openId:{}",param.getOpenId());
            throw new ServiceException(ServiceExceptionEnum.USER_NOT_EXISTS);
        }
        return TokenHelper.token(user.getId(), WX_FROM);
    }


    private WxMaUserInfo getWxAppUserInfo(MiniAppLoginParam miniAppLoginParam) throws WxErrorException {
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(miniAppLoginParam.getCode().trim());
        return wxMaService.getUserService()
                .getUserInfo(session.getSessionKey().trim(),
                        miniAppLoginParam.getEncryptedData().trim(),
                        miniAppLoginParam.getIv().trim());
    }

    private Long createUserInfo( WxMaUserInfo wxMaUserInfo,Long fromId){
        Date currentDate = new Date();
        DiUser user = new DiUser();
        user.setOpenId(wxMaUserInfo.getOpenId());
        user.setNickName(wxMaUserInfo.getNickName());
        user.setProvince(wxMaUserInfo.getProvince());
        user.setCity(wxMaUserInfo.getCity());
        user.setAvatar(wxMaUserInfo.getAvatarUrl());
        user.setSex(Integer.valueOf(wxMaUserInfo.getGender()));
        user.setCreator(CommonConst.DEFAULT_ID);
        user.setModifier(CommonConst.DEFAULT_ID);
        user.setCreateDate(currentDate);
        user.setModifyDate(currentDate);
        user.setIsDelete(IsDeleteEnum.UN_DELETE.value());

        return userService.createUser(user,fromId);
    }
}
