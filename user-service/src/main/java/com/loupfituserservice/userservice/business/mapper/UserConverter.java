package com.loupfituserservice.userservice.business.mapper;

import com.loupfituserservice.userservice.business.record.user.in.UserRequest;
import com.loupfituserservice.userservice.business.record.user.out.UserResponse;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User toEntity(UserRequest request);

    UserResponse toResponse(User entity);

    List<UserResponse> toResponseList(List<User> entities);
}
