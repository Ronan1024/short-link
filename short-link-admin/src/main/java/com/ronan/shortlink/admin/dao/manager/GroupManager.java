package com.ronan.shortlink.admin.dao.manager;

import com.baosight.database.core.annotation.Manager;
import com.baosight.database.core.manager.impl.BaseManagerImpl;
import com.ronan.shortlink.admin.dao.entity.Group;
import com.ronan.shortlink.admin.dao.mapper.GroupMapper;

import java.util.List;

/**
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
@Manager
public class GroupManager extends BaseManagerImpl<GroupMapper, Group> {


    /**
     * 根据用户名获取短连接列表
     * @param username 用户名
     */
    public  List<Group> groupListByUsername(String username) {
        return lambdaQuery()
                .eq(Group::getUsername, username)
                .eq(Group::getDeleted, Boolean.FALSE)
                .list();
    }
}
