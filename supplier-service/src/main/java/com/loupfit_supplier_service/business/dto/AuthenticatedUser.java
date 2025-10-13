package com.loupfit_supplier_service.business.dto;

import com.loupfit_supplier_service.infrastructure.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticatedUser {

    private String username;
    private UserRole role;
}
