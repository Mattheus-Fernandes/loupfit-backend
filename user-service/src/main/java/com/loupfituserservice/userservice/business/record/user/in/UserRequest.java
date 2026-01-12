package com.loupfituserservice.userservice.business.record.user.in;

import com.loupfituserservice.userservice.infrastructure.enums.UserRole;

public record UserRequest(
        String name,
        String lastname,
        String username,
        String password,
        UserRole role
) { }
