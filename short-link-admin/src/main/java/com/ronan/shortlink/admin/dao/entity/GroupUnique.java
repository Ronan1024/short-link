package com.ronan.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author L.J.Ran
 * @TableName group_unique
 */
@Data
@Builder
@TableName(value ="group_unique")
public class GroupUnique {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分组id
     */
    private String groupId;
}