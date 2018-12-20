package com.dl.diligent.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 小程序获取Token参数
 *
 * @author dail
 * @since 2018/11/12
 */
@Data
public class MiniAppLoginParam extends BaseParam {

    /**
     * 微信的OpenID
     */
    private String openId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 微信返回的code
     */
    @NotNull(message = "code不能为空")
    private String code;

    /**
     * 小程序的加密数据
     */
    @NotNull(message = "encryptedData不能为空")
    private String encryptedData;

    /**
     * 小程序的加密偏移
     */
    @NotNull(message = "iv不能为空")
    private String iv;

    /**
     * 分享者id
     */
    private Long fromId;
}
