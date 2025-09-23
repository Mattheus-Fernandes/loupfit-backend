package com.loupfituserservice.userservice.business.dto.employee;

import com.loupfituserservice.userservice.infrastructure.enums.EmployeeRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeCreateDTO {

    private String name;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String cpf;
    private EmployeeRole role;
    private Boolean active;
}
