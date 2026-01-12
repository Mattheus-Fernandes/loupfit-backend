package com.loupfituserservice.userservice.business.record.user.in;

import com.loupfituserservice.userservice.infrastructure.enums.UserRole;

public record UserRoleRequest(
        UserRole role
) { }
