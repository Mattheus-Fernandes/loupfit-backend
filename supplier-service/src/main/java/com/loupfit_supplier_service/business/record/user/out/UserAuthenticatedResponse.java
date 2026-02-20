package com.loupfit_supplier_service.business.record.user.out;


import com.loupfit_supplier_service.infrastructure.enums.UserRole;

public record UserAuthenticatedResponse(
        String username,
        UserRole role
) {
}
