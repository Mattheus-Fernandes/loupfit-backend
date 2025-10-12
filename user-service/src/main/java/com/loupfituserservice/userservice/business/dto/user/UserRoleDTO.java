package com.loupfituserservice.userservice.business.dto.user;

import com.loupfituserservice.userservice.infrastructure.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRoleDTO {

    private UserRole role;
}
