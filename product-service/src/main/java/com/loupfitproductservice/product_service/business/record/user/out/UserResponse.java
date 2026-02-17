package com.loupfitproductservice.product_service.business.record.user.out;

import com.loupfitproductservice.product_service.infrastructure.enums.UserRole;

public record UserResponse(
        String username,
        UserRole role
) {
}
