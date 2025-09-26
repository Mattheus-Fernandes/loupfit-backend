package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.CustomerService;
import com.loupfituserservice.userservice.business.dto.customer.CustomerDTO;
import com.loupfituserservice.userservice.business.dto.customer.CustomerReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerReqDTO dto) {
        return ResponseEntity.ok(customerService.addCustomer(dto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
        return  ResponseEntity.ok(customerService.filterAllCustomers());
    }

    @GetMapping("/current")
    public ResponseEntity<CustomerDTO> findByCurrentUser() {
        return ResponseEntity.ok(customerService.filterCurrentUser());
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(
            @RequestBody CustomerReqDTO dto
    ){
        return ResponseEntity.ok(customerService.editCustomer(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(customerService.removeCustomer(id));
    }
}
