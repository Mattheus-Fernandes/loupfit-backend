package com.loupfitorderservice.order_service.business.dto.user;

import com.loupfitorderservice.order_service.infrastructure.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private String username;
    private UserRole role;
}