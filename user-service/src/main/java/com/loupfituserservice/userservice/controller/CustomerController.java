package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.CustomerService;
import com.loupfituserservice.userservice.business.record.customer.in.CustomerRequest;
import com.loupfituserservice.userservice.business.record.customer.out.CustomerResponse;
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
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.addCustomer(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return  ResponseEntity.ok(customerService.filterAllCustomers());
    }

    @GetMapping("/current")
    public ResponseEntity<CustomerResponse> findByCurrentUser() {
        return ResponseEntity.ok(customerService.filterCurrentUser());
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestBody CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.editCustomer(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(customerService.removeCustomer(id));
    }
}
