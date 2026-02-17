package com.loupfitassetservice.asset_service.business.record.user.out;

import com.loupfitassetservice.asset_service.infrastructure.enums.UserRole;

public record UserAuthenticatedResponse(
        String username,
        UserRole role
) {
}
