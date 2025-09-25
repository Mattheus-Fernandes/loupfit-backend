package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.EmployeeConverter;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeCreateDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Employee;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictExcpetion;
import com.loupfituserservice.userservice.infrastructure.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeConverter employeeConverter;

    public EmployeeDTO addEmployee(EmployeeCreateDTO dto) {

        validateEmployee(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        Employee newEmployee = employeeConverter.createEmployee(dto);

        return employeeConverter.employeeDTO(employeeRepository.save(newEmployee));
    }

    public void validateEmployee(EmployeeCreateDTO dto) {

        if (employeeRepository.existsByUsername(dto.getUsername())) {
            throw new ConflictExcpetion("Já existe funcionário com esse usuário " + dto.getUsername());

        }

        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictExcpetion("Já existe funcionário com esse e-mail " + dto.getEmail());

        }

        if (employeeRepository.existsByCpf(dto.getCpf())) {
            throw new ConflictExcpetion("Já existe funcionário com esse CPF " + dto.getCpf());

        }

    }

    public List<EmployeeDTO> filterAllEmployees() {
        return employeeConverter.employeeDTOList(employeeRepository.findAll());
    }

}
