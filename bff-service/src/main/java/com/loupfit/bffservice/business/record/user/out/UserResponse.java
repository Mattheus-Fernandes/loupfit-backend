package com.loupfit.bffservice.business.record.user.out;

import com.loupfit.bffservice.infrastructure.enums.UserRole;

public record UserResponse(
        Long id,
        String name,
        String lastname,
        String username,
        UserRole role
) {
}
