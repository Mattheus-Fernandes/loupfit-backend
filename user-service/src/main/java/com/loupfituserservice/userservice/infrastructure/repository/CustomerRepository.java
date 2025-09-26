package com.loupfituserservice.userservice.infrastructure.repository;

import com.loupfituserservice.userservice.business.dto.customer.CustomerDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    Optional<Customer> findByUsername(String username);
}
