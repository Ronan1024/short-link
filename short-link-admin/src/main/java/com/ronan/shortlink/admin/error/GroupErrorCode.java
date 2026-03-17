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
    /**
     * 分组标识生成频繁
     */
    GROUP_ID_GENERATION_FREQUENT("B000021", "分组标识生成频繁"),
    ;

    GroupErrorCode(String code, String message) {
        initEnum(code, message);
    }
}
