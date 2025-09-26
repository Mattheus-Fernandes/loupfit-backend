package com.loupfituserservice.userservice.business.dto.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerReqDTO {

    private String name;
    private String lastname;
    private char gender;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String cpf;
    private String city;
    private String neighbour;
    private String street;
    private Integer number;
    private String uf;
}
