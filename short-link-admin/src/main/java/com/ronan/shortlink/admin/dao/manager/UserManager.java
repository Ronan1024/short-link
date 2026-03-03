package com.ronan.shortlink.admin.dao.manager;

import com.baosight.database.core.annotation.Manager;
import com.baosight.database.core.manager.impl.BaseManagerImpl;
import com.baosight.web.core.exception.ApiException;
import com.ronan.common.utils.Assert;
import com.ronan.shortlink.admin.dao.entity.User;
import com.ronan.shortlink.admin.dao.mapper.UserMapper;
import com.ronan.shortlink.admin.error.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
@Manager
@RequiredArgsConstructor
public class UserManager extends BaseManagerImpl<UserMapper, User> {
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     */
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }


    /**
     * 添加用户名是否存在
     *
     * @param username 用户名
     */
    public void addUsername(String username) {
        userRegisterCachePenetrationBloomFilter.add(username);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(User user) {
        try {
            boolean save = save(user);
            Assert.isFalse(save, ApiException.supplier(UserErrorCode.USER_SAVE_ERROR));
            addUsername(user.getUsername());
        } catch (DuplicateKeyException e) {
            throw new ApiException(UserErrorCode.USER_EXIST);
        }

    }
}
