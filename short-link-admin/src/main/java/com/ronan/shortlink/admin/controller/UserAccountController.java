package com.ronan.shortlink.admin.controller;

import com.ronan.shortlink.admin.dto.req.AccountRegisterReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户账号管理
 *
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/account")
public class UserAccountController {


    /**
     * 注册用户
     */
    @PostMapping("/v1/register")
    public Boolean register(AccountRegisterReqDTO register) {
    }



}
