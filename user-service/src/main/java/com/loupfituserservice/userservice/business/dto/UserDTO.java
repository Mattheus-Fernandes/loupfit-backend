package com.loupfituserservice.userservice.business.dto;

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
    private Long role;
}
