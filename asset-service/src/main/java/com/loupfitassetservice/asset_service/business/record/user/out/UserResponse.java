package com.loupfitassetservice.asset_service.business.record.user.out;
import com.loupfitassetservice.asset_service.infrastructure.enums.UserRole;

public record UserResponse(
        String username,
        UserRole role
) {
}
