package com.loupfitorderservice.order_service.business.record.user.out;

import com.loupfitorderservice.order_service.infrastructure.enums.UserRole;

public record UserResponse(
        String username,
        UserRole role
) {
}
