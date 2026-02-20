package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.record.customer.in.CustomerRequest;
import com.loupfituserservice.userservice.business.record.customer.out.CustomerResponse;
import com.loupfituserservice.userservice.business.mapper.CustomerConverter;
import com.loupfituserservice.userservice.business.mapper.CustomerUpdateConverter;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfituserservice.userservice.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final CustomerUpdateConverter customerUpdateConverter;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponse addCustomer(CustomerRequest request) {
        validateCustomer(request);

        String password = request.password() != null ? passwordEncoder.encode(request.password()) : null;

        CustomerRequest req = new CustomerRequest(
                request.name(),
                request.lastname(),
                request.gender(),
                request.username(),
                password,
                request.email(),
                request.phone(),
                request.cpf(),
                request.city(),
                request.neighbour(),
                request.street(),
                request.number(),
                request.uf()
        );

        Customer entity = customerConverter.toEntity(req);

        return customerConverter.toResponse(customerRepository.save(entity));

    }

    public void validateCustomer(CustomerRequest request) {

        if (customerRepository.existsByUsername(request.username())) {
            throw new ConflictException("Usuário(a) já em uso " + request.username());
        }

        if (customerRepository.existsByEmail(request.email())) {
            throw new ConflictException("E-mail já em uso  " + request.email());
        }

        if (customerRepository.existsByCpf(request.cpf())) {
            throw new ConflictException("CPF já cadastrado  " + request.cpf());
        }

    }

    public List<CustomerResponse> filterAllCustomers() {
        return customerConverter.toResponseList(customerRepository.findAll());
    }

    public CustomerResponse filterCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer entity = customerRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado " + username)
        );

        return customerConverter.toResponse(entity);
    }

    public CustomerResponse editCustomer(CustomerRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer entity = customerRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado " + username)
        );

        Customer entityEdit = customerUpdateConverter.doUpdate(request, entity);

        if (request.password() != null) {
            entityEdit.setPassword(passwordEncoder.encode(request.password()));
        } else {
            entityEdit.setPassword(entity.getPassword());
        }

        return customerConverter.toResponse(customerRepository.save(entityEdit));

    }

    public CustomerResponse removeCustomer(Long id) {
        Customer entity = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        customerRepository.deleteById(id);

        return customerConverter.toResponse(entity);
    }
}
