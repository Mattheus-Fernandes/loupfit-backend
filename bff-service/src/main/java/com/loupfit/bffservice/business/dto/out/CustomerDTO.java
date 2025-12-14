package com.loupfit.bffservice.business.dto.out;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String lastname;
    private Character gender;
    private String username;
    private String email;
    private String phone;
    private String cpf;
    private String city;
    private String neighbour;
    private String street;
    private Integer number;
    private String uf;
}
