package com.loupfit.bffservice.business.dto.in;

import com.loupfit.bffservice.infrastructure.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRoleDTO {

    private UserRole role;
}
