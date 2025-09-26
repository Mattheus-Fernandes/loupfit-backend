package com.loupfituserservice.userservice.business.converter;

import com.loupfituserservice.userservice.business.dto.customer.CustomerDTO;
import com.loupfituserservice.userservice.business.dto.customer.CustomerReqDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerConverter {

    public Customer createCustomer(CustomerReqDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .gender(dto.getGender())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .cpf(dto.getCpf())
                .city(dto.getCity())
                .neighbour(dto.getNeighbour())
                .street(dto.getStreet())
                .number(dto.getNumber())
                .uf(dto.getUf())
                .build();
    }

    public CustomerDTO customerDTO(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .gender(entity.getGender())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .cpf(entity.getCpf())
                .city(entity.getCity())
                .neighbour(entity.getNeighbour())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .uf(entity.getUf())
                .build();
    }

    public List<CustomerDTO> customerDTOList(List<Customer> entities) {
        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>();

        for (Customer customer: entities) {
            customerDTOS.add(customerDTO(customer));
        }

        return customerDTOS;
    }

}
