package com.ronan.shortlink.admin.dao.manager;

import com.baosight.database.core.annotation.Manager;
import com.baosight.database.core.manager.impl.BaseManagerImpl;
import com.ronan.shortlink.admin.dao.entity.Group;
import com.ronan.shortlink.admin.dao.entity.GroupUnique;
import com.ronan.shortlink.admin.dao.mapper.GroupMapper;
import com.ronan.shortlink.admin.dao.mapper.GroupUniqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
@Manager
@RequiredArgsConstructor
public class GroupManager extends BaseManagerImpl<GroupMapper, Group> {
    private final GroupUniqueMapper groupUniqueMapper;


    /**
     * 根据用户名获取短连接列表
     *
     * @param username 用户名
     */
    public List<Group> groupListByUsername(String username) {
        return lambdaQuery()
                .eq(Group::getUsername, username)
                .eq(Group::getDeleted, Boolean.FALSE)
                .list();
    }

    /**
     * 将新组及其唯一标识符插入数据库。
     * <p>
     * 该方法首先尝试在“group_unique”表中插入唯一的组ID（groupUnique）。
     * 然后将组内细节保存到“组”表中。如果发生重复密钥异常
     * （例如，组ID已存在于“group_unique”），操作将优雅失败并返回false。
     * <p>
     *
     * @param group 包含待插入的组细节的组实体。
     * @return 如果插入成功，则为true;如果操作因重复键而失败，则为false。
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean insertGroup(Group group) {
        GroupUnique groupUnique = GroupUnique.builder().groupId(group.getGid()).build();
        try {
            groupUniqueMapper.insert(groupUnique);
            return save(group);
        } catch (DuplicateKeyException e) {
            return false;
        }
    }
}
