package com.ronan.shortlink.admin.service;

import com.ronan.shortlink.admin.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ronan.shortlink.admin.dto.req.AccountRegisterReqDTO;

/**
 * @author longjiangran
 * @description 针对表【user】的数据库操作Service
 * @createDate 2026-02-27 14:09:49
 */
public interface UserService extends IService<User> {


    /**
     * 注册用户账号
     *
     * @param register 账号注册请求参数
     */
    Boolean register(AccountRegisterReqDTO register);


}
