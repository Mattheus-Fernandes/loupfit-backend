package com.loupfitassetservice.asset_service.business.dto;

import com.loupfitassetservice.asset_service.infrastructure.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticatedUserDTO {

    private String username;
    private UserRole role;
}
