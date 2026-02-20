package com.loupfit.bffservice.business.record.user.in;

import com.loupfit.bffservice.infrastructure.enums.UserRole;

public record UserRequest(
        String name,
        String lastname,
        String username,
        String password,
        UserRole role
) {
}
