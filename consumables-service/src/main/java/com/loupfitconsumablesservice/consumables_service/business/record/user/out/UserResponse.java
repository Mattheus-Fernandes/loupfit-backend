package com.loupfitconsumablesservice.consumables_service.business.record.user.out;

import com.loupfitconsumablesservice.consumables_service.infrastructure.enums.UserRole;

public record UserResponse(
        String username,
        UserRole role
) {
}
