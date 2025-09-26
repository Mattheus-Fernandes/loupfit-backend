package com.loupfituserservice.userservice.business.dto.employee;

import com.loupfituserservice.userservice.infrastructure.enums.EmployeeRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeRoleDTO {

    private EmployeeRole role;
}
