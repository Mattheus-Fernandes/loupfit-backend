package com.loupfit.bffservice.business.record.user.in;

import com.loupfit.bffservice.infrastructure.enums.UserRole;

public record UserRoleRequest(
        UserRole role
) {
}
