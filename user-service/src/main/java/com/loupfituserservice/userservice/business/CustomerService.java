package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.CustomerConverter;
import com.loupfituserservice.userservice.business.dto.customer.CustomerDTO;
import com.loupfituserservice.userservice.business.dto.customer.CustomerReqDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictExcpetion;
import com.loupfituserservice.userservice.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final PasswordEncoder passwordEncoder;

    public CustomerDTO addCustomer(@RequestBody CustomerReqDTO dto) {
        validateCustomer(dto);

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        Customer entity = customerConverter.createCustomer(dto);

        return customerConverter.customerDTO(customerRepository.save(entity));

    }

    public void validateCustomer(CustomerReqDTO dto) {

        if (customerRepository.existsByUsername(dto.getUsername())) {
            throw new ConflictExcpetion("Usuário(a) já em uso " + dto.getUsername());
        }

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictExcpetion("E-mail já em uso  " + dto.getEmail());
        }

        if (customerRepository.existsByCpf(dto.getCpf())) {
            throw new ConflictExcpetion("CPF já cadastrado  " + dto.getCpf());
        }

    }

    public List<CustomerDTO> filterAllCustomers() {
        return customerConverter.customerDTOList(customerRepository.findAll());
    }


}
