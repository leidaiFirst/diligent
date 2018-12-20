package com.dl.diligent.service;


import com.dl.diligent.dto.MiniAppLoginParam;
import com.dl.diligent.dto.UserTokenResult;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author dail
 * @description
 * @since 2018/11/19 10:20
 */
public interface LoginService {
    /**
     * 用户注册接口
     * @param loginParam
     * @return
     */
    UserTokenResult login(MiniAppLoginParam loginParam) throws Exception;

    /**
     * 用户authCode 刷新
     * @param loginParam
     * @return
     */
    String refresh(MiniAppLoginParam loginParam) throws WxErrorException;

}
