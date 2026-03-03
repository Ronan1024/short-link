package com.ronan.shortlink.admin.constant;

import com.ronan.common.string.text.StringFormatter;

/**
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
public class RedisCacheConstant {

    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user_register:{}";
    /**
     * 分组创建分布式锁
     */
    private static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group_create:{}";


    /**
     * 分组创建分布式锁
     *
     * @param username 用户名
     */
    public static String getLockGroupCreateKey(String username) {
        return StringFormatter.format(LOCK_GROUP_CREATE_KEY, username);
    }
}
