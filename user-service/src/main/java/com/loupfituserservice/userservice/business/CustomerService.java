package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.CustomerConverter;
import com.loupfituserservice.userservice.business.dto.customer.CustomerDTO;
import com.loupfituserservice.userservice.business.dto.customer.CustomerReqDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfituserservice.userservice.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            throw new ConflictException("Usuário(a) já em uso " + dto.getUsername());
        }

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("E-mail já em uso  " + dto.getEmail());
        }

        if (customerRepository.existsByCpf(dto.getCpf())) {
            throw new ConflictException("CPF já cadastrado  " + dto.getCpf());
        }

    }

    public List<CustomerDTO> filterAllCustomers() {
        return customerConverter.customerDTOList(customerRepository.findAll());
    }

    public CustomerDTO filterCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer entity = customerRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado " + username)
        );

        return customerConverter.customerDTO(entity);
    }

    public CustomerDTO editCustomer(CustomerReqDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer entity = customerRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado " + username)
        );

        Customer entityEdit = customerConverter.updateCustomer(dto, entity);

        if (dto.getPassword() != null) {
            entityEdit.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            entityEdit.setPassword(entity.getPassword());
        }

        return customerConverter.customerDTO(customerRepository.save(entityEdit));

    }

    public CustomerDTO removeCustomer(Long id) {
        Customer entity = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        customerRepository.deleteById(id);

        return customerConverter.customerDTO(entity);
    }
}
