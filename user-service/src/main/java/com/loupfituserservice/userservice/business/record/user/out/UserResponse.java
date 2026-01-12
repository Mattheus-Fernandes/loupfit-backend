package com.loupfituserservice.userservice.business.record.user.out;

import com.loupfituserservice.userservice.infrastructure.enums.UserRole;

public record UserResponse(
        Long id,
        String name,
        String lastname,
        String username,
        UserRole role
) { }
