package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.EmployeeService;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeCreateDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeCreateDTO dto) {
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.filterAllEmployees());
    }
}
