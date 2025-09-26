package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.EmployeeConverter;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeActiveDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeReqDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeRoleDTO;
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

    public EmployeeDTO addEmployee(EmployeeReqDTO dto) {

        validateEmployee(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        Employee newEmployee = employeeConverter.createEmployee(dto);

        return employeeConverter.employeeDTO(employeeRepository.save(newEmployee));
    }

    public void validateEmployee(EmployeeReqDTO dto) {

        if (employeeRepository.existsByUsername(dto.getUsername())) {
            throw new ConflictExcpetion("Já existe funcionário(a) com esse usuário " + dto.getUsername());

        }

        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictExcpetion("Já existe funcionário(a) com esse e-mail " + dto.getEmail());

        }

        if (employeeRepository.existsByCpf(dto.getCpf())) {
            throw new ConflictExcpetion("Já existe funcionário(a) com esse CPF " + dto.getCpf());

        }

    }

    public List<EmployeeDTO> filterAllEmployees() {
        return employeeConverter.employeeDTOList(employeeRepository.findAll());
    }

    public EmployeeDTO filterByUsername(String username) {
        try {

            return employeeConverter.employeeDTO(
                    employeeRepository.findByUsername(username).orElseThrow(
                            () -> new ConflictExcpetion("Usuário não encontrado")
                    )
            );

        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public EmployeeDTO editEmployee(Long id, EmployeeReqDTO dto) {

        Employee entity = employeeRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        Employee entityEdit = employeeConverter.employeeUpdate(dto, entity);

        return employeeConverter.employeeDTO(employeeRepository.save(entityEdit));
    }

    public EmployeeDTO editRoleEmployee(Long id, EmployeeRoleDTO dto) {

        Employee entity = employeeRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());

        }

        return employeeConverter.employeeDTO(employeeRepository.save(entity));
    }

    public EmployeeDTO editActiveEmployee(Long id, EmployeeActiveDTO dto) {

        Employee entity = employeeRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        if (dto.getActive() != null) {
            entity.setActive(dto.getActive());

        }

        return employeeConverter.employeeDTO(employeeRepository.save(entity));
    }

    public EmployeeDTO removeEmployee(Long id) {
        Employee entity = employeeRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        employeeRepository.deleteById(id);

        return employeeConverter.employeeDTO(entity);
    }

}
