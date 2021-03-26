package com.sectest.seckilldemo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),

    //登陆模块
    LOGIN_ERROR(500210,"用户名或密码错误"),
    MOBILE_ERROR(500211,"手机号格式错误"),
    BIND_ERROR(500212,"绑定异常");

    private final Integer code;
    private final String message;
}
