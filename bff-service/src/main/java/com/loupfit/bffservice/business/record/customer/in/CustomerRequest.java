package com.loupfit.bffservice.business.record.customer.in;

public record CustomerRequest(
        String name,
        String lastname,
        String gender,
        String username,
        String password,
        String email,
        String phone,
        String cpf,
        String city,
        String neighbour,
        String street,
        Integer number,
        String uf
) {
}
