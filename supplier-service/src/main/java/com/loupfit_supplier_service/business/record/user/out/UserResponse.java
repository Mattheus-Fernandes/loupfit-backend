package com.loupfit_supplier_service.business.record.user.out;


import com.loupfit_supplier_service.infrastructure.enums.UserRole;

public record UserResponse(
        String username,
        UserRole role
) {
}
