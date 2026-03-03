package com.ronan.shortlink.admin.service;

import com.ronan.shortlink.admin.dao.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author longjiangran
 * @description 针对表【group】的数据库操作Service
 * @createDate 2026-03-02 13:46:22
 */
public interface GroupService extends IService<Group> {

    /**
     * 新增短连接分组
     * @param username 用户名
     * @param groupName 端连接分组名称
     */
    void saveGroup(String username, String groupName);

}
