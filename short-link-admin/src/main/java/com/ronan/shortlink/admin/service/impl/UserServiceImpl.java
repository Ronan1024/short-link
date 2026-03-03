package com.ronan.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baosight.web.core.exception.ApiException;
import com.ronan.common.utils.Assert;
import com.ronan.shortlink.admin.convert.UserConvert;
import com.ronan.shortlink.admin.dao.entity.User;
import com.ronan.shortlink.admin.dao.manager.GroupManager;
import com.ronan.shortlink.admin.dao.manager.UserManager;
import com.ronan.shortlink.admin.dto.req.AccountRegisterReqDTO;
import com.ronan.shortlink.admin.error.UserErrorCode;
import com.ronan.shortlink.admin.service.GroupService;
import com.ronan.shortlink.admin.service.UserService;
import com.ronan.shortlink.admin.dao.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static com.ronan.shortlink.admin.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;


/**
 * @author longjiangran
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2026-02-27 14:09:49
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserManager userManager;
    private final RedissonClient redissonClient;
    private final GroupService groupService;


    /**
     * 注册用户账号
     *
     * @param register 账号注册请求参数
     */
    @Override
    public Boolean register(AccountRegisterReqDTO register) {
        if (userManager.hasUsername(register.getUsername())) {
            throw new ApiException(UserErrorCode.USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + register.getUsername());
        if (lock.tryLock()) {
            throw new ApiException(UserErrorCode.USER_NAME_EXIST);
        }
        try {
            User user = UserConvert.INSTANCE.toEntity(register);
            user.setDeleted(Boolean.FALSE);
            userManager.saveUser(user);
            // 保存分组信息

        } finally {
            lock.unlock();
        }


        return Boolean.TRUE;
    }
}




