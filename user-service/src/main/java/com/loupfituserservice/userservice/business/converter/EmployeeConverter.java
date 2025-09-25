package com.loupfituserservice.userservice.business.converter;

import com.loupfituserservice.userservice.business.dto.employee.EmployeeCreateDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeDTO;
import com.loupfituserservice.userservice.infrastructure.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeConverter {

    public Employee createEmployee(EmployeeCreateDTO dto) {
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

        for (Employee employee: entities) {
            employeeDTOList.add(employeeDTO(employee));
        }

        return employeeDTOList;

    }

}
