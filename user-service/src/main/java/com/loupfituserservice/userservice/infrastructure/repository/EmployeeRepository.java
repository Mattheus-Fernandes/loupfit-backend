package com.loupfituserservice.userservice.infrastructure.repository;

import com.loupfituserservice.userservice.infrastructure.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    Optional<Employee> findByUsername(String username);

}
