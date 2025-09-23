package com.loupfituserservice.userservice.business.dto.user;

import com.loupfituserservice.userservice.infrastructure.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserReqDTO {

    private String name;
    private String lastname;
    private String username;
    private String password;
    private UserRole role;
}
