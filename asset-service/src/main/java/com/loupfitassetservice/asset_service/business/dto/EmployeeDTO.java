package com.loupfitassetservice.asset_service.business.dto;

import com.loupfitassetservice.asset_service.infrastructure.enums.EmployeeRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {

    private String username;
    private EmployeeRole role;
}
