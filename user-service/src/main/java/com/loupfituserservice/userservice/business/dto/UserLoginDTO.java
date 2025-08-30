package com.loupfituserservice.userservice.business.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {

    private String username;
    private String password;
}
