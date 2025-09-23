package com.loupfituserservice.userservice.business.dto.user;

import com.loupfituserservice.userservice.infrastructure.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String lastname;
    private String username;
    private UserRole role;
}
