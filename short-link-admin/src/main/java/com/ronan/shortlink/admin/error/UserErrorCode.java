package com.ronan.shortlink.admin.error;

import com.baosight.web.core.exception.IErrorEnum;

/**
 * 用户错误码
 *
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
public enum UserErrorCode implements IErrorEnum<String> {
    /**
     * 用户名已存在
     */
    USER_NAME_EXIST("B000010", "用户名已存在"),
    /**
     * 用户保存失败
     */
    USER_SAVE_ERROR("B000011", "用户保存失败"),
    /**
     * 用户记录已存在
     */
    USER_EXIST("B000012", "用户记录已存在"),
    ;


    UserErrorCode(String code, String msg) {
        initEnum(code, msg);
    }
}
