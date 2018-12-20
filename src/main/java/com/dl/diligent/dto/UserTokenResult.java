package com.dl.diligent.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author dail
 * @description
 * @since 2018/11/19 16:26
 */
@Getter
@Setter
public class UserTokenResult implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 注册成功返回请求头
     */
    private String authCode;
}
