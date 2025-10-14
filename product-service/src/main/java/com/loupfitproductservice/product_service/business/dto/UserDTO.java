package com.loupfitproductservice.product_service.business.dto;

import com.loupfitproductservice.product_service.infrastructure.enums.UserRole;
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
