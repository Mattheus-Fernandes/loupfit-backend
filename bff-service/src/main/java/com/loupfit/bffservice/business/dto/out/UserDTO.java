package com.loupfit.bffservice.business.dto.out;

import com.loupfit.bffservice.infrastructure.enums.UserRole;
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
