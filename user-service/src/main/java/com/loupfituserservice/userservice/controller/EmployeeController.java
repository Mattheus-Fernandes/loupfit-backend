package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.EmployeeService;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeActiveDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeReqDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeDTO;
import com.loupfituserservice.userservice.business.dto.employee.EmployeeRoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeReqDTO dto) {
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.filterAllEmployees());
    }

    @GetMapping("/search")
    public ResponseEntity<EmployeeDTO> findByUsername(
            @RequestParam(required = true) String username
    ) {
        return ResponseEntity.ok(employeeService.filterByUsername(username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeReqDTO dto
    ) {
        return ResponseEntity.ok(employeeService.editEmployee(id, dto));
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<EmployeeDTO> updateRoleEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRoleDTO dto
    ) {
        return ResponseEntity.ok(employeeService.editRoleEmployee(id, dto));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<EmployeeDTO> updateActiveEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeActiveDTO dto
    ) {
        return ResponseEntity.ok(employeeService.editActiveEmployee(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(employeeService.removeEmployee(id));
    }
}
