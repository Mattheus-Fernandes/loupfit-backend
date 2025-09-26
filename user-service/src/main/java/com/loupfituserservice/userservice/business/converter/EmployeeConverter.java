package com.loupfituserservice.userservice.business.converter;

import com.loupfituserservice.userservice.business.dto.employee.EmployeeReqDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeConverter {

    public Employee createEmployee(EmployeeReqDTO dto) {
        return Employee.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .cpf(dto.getCpf())
                .role(dto.getRole())
                .active(dto.getActive())
                .build();
    }

    public EmployeeDTO employeeDTO(Employee entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .cpf(entity.getCpf())
                .role(entity.getRole())
                .active(entity.getActive())
                .build();
    }

    public List<EmployeeDTO> employeeDTOList(List<Employee> entities) {

        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();

        for (Employee employee : entities) {
            employeeDTOList.add(employeeDTO(employee));
        }

        return employeeDTOList;

    }

    public Employee employeeUpdate(EmployeeReqDTO dto, Employee entity) {
        return Employee.builder()
                .id(entity.getId())
                .name(dto.getName() != null ? dto.getName() : entity.getName())
                .lastname(dto.getLastname() != null ? dto.getLastname() : entity.getLastname())
                .username(dto.getUsername() != null ? dto.getUsername() : entity.getUsername())
                .password(dto.getPassword() != null ? dto.getPassword() : entity.getPassword())
                .email(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .phone(dto.getPhone() != null ? dto.getPhone() : entity.getPhone())
                .cpf(dto.getCpf() != null ? dto.getCpf() : entity.getCpf())
                .role(dto.getRole() != null ? dto.getRole() : entity.getRole())
                .active(dto.getActive() != null ? dto.getActive() : entity.getActive())
                .build();
    }

}
