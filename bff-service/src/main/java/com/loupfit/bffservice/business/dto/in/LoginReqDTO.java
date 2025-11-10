package com.loupfit.bffservice.business.dto.in;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReqDTO {

    private String username;
    private String password;
}
