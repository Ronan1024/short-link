package com.ronan.shortlink.admin.error;

import com.baosight.web.core.exception.ErrorCode;
import com.baosight.web.core.exception.IErrorEnum;

/**
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
public enum GroupErrorCode  implements IErrorEnum<String> {
    /**
     * 超出最大分组数
     */
    EXCEEDED_MAX_GROUP_COUNT("B000020", "超出最大分组数: {}"),
    ;

    GroupErrorCode(String code, String message) {
        initEnum(code, message);
    }
}
