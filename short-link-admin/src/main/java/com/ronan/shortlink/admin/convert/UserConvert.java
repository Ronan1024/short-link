package com.ronan.shortlink.admin.convert;

import com.ronan.shortlink.admin.dao.entity.User;
import com.ronan.shortlink.admin.dto.req.AccountRegisterReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @program: short-link
 * @description:
 * @author: L.J.Ran
 * @create: 2026/3/2
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deletionTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    User toEntity(AccountRegisterReqDTO register);
}
