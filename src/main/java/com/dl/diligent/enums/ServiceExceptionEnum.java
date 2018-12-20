package com.dl.diligent.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceExceptionEnum {
    /**
     * 系统错误
     */
    SYS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "系统错误"),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "用户已经存在"),
    USER_NOT_EXISTS(HttpStatus.FORBIDDEN, "用户不存在"),
    USER_NOT_LOGIN(HttpStatus.FORBIDDEN, "用户未登录"),
    SYSTEM_BUSY(HttpStatus.INTERNAL_SERVER_ERROR,"系统忙，请稍后再试"),

    /**
     * 业务逻辑错误
     */
    BUSINESS_NOT_EXIT(HttpStatus.INTERNAL_SERVER_ERROR,"业务不存在"),
    PARAM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"参数错误"),

    /**
     * 积分商城错误码
     */
    UNDER_STOCK(HttpStatus.INTERNAL_SERVER_ERROR,"库存不足"),
    UNDER_SCORE(HttpStatus.INTERNAL_SERVER_ERROR,"积分不足");

    /**
     * 错误码
     */
    private HttpStatus status;

    /**
     * 错误信息
     */
    private String message;

    private ServiceExceptionEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
