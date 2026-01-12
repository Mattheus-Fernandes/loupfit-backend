package com.loupfituserservice.userservice.business.record.customer.out;

public record CustomerResponse(
        Long id,
        String name,
        String lastname,
        Character gender,
        String username,
        String email,
        String phone,
        String cpf,
        String city,
        String neighbour,
        String street,
        Integer number,
        String uf
) { }
