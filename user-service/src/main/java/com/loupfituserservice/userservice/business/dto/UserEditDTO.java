package com.loupfituserservice.userservice.business.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEditDTO {

    private String name;
    private String lastname;
    private String username;
    private String password;
    private Long role;
}
