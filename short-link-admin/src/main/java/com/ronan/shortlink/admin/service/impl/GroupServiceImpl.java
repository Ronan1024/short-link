package com.ronan.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baosight.web.core.exception.ApiException;
import com.ronan.common.utils.Assert;
import com.ronan.shortlink.admin.constant.RedisCacheConstant;
import com.ronan.shortlink.admin.dao.entity.Group;
import com.ronan.shortlink.admin.dao.entity.GroupUnique;
import com.ronan.shortlink.admin.dao.manager.GroupManager;
import com.ronan.shortlink.admin.error.GroupErrorCode;
import com.ronan.shortlink.admin.service.GroupService;
import com.ronan.shortlink.admin.dao.mapper.GroupMapper;
import com.ronan.shortlink.admin.toolkit.RandomGenerator;
import jodd.util.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static com.ronan.shortlink.admin.error.GroupErrorCode.GROUP_ID_GENERATION_FREQUENT;

/**
 * @author longjiangran
 * @description 针对表【group】的数据库操作Service实现
 * @createDate 2026-03-02 13:46:22
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    private final RedissonClient redissonClient;

    private final RBloomFilter<String> gidRegisterCachePenetrationBloomFilter;

    private final GroupManager groupManager;

    @Value("${short-link.group.max-num}")
    private Integer groupMaxNum;


    /**
     * 新增短连接分组
     *
     * @param username  用户名
     * @param groupName 端连接分组名称
     */
    @Override
    public void saveGroup(String username, String groupName) {
        RLock lock = redissonClient.getLock(RedisCacheConstant.getLockGroupCreateKey(username));
        lock.lock();

        try {
            List<Group> groupList = groupManager.groupListByUsername(username);
            Assert.isTrue(!CollectionUtils.isEmpty(groupList) && groupList.size() == groupMaxNum,
                    ApiException.supplier(GroupErrorCode.EXCEEDED_MAX_GROUP_COUNT, groupMaxNum));

            String groupId = saveGroupUniqueReturnGid();
            Assert.isNull(groupId, ApiException.supplier(GROUP_ID_GENERATION_FREQUENT));

            Group group = Group.builder()
                    .gid(groupId)
                    .sortOrder(0)
                    .username(username)
                    .name(groupName)
                    .build();
            boolean insertResult = groupManager.insertGroup(group);
            if (insertResult) {
                gidRegisterCachePenetrationBloomFilter.add(groupId);
            }
        } finally {
            lock.unlock();
        }
    }

    private String saveGroupUniqueReturnGid() {
        String groupId = RandomGenerator.generateRandom();
        if (gidRegisterCachePenetrationBloomFilter.contains(groupId)) {
            return null;
        }
        return groupId;
    }

}




