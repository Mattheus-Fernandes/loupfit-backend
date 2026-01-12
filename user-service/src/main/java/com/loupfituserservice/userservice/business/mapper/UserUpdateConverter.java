package com.loupfituserservice.userservice.business.mapper;

import com.loupfituserservice.userservice.business.record.user.in.UserRequest;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdateConverter {

    User doUpdate(UserRequest request, @MappingTarget User entity);
}
