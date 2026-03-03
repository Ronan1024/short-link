package com.ronan.shortlink.admin.error;

import com.baosight.web.core.exception.IErrorEnum;

/**
 * admin 基础异常错误码定义
 *
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
public enum BaseErrorCode implements IErrorEnum<String> {
    /**
     * 用户端一级错误码
     */
    CLIENT_ERROR("A000001", "用户端错误"),

    /**
     * 一级系统执行错误
     */
    SERVICE_ERROR("B000001", "系统执行错误"),

    ;

    BaseErrorCode(String code, String msg) {
        initEnum(code, msg);
    }
}
