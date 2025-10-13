package com.loupfit_supplier_service.business.dto;

import com.loupfit_supplier_service.infrastructure.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private String username;
    private UserRole role;
}
