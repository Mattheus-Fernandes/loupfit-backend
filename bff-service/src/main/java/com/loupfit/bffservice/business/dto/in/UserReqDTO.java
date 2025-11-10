package com.loupfit.bffservice.business.dto.in;

import com.loupfit.bffservice.infrastructure.enums.UserRole;
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
