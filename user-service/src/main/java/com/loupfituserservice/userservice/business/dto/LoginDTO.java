package com.loupfituserservice.userservice.business.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    private String username;
    private String password;
}
