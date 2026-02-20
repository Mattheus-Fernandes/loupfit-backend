package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.CustomerService;
import com.loupfit.bffservice.business.record.customer.in.CustomerRequest;
import com.loupfit.bffservice.business.record.customer.out.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Registers and searches of customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Register Customers", description = "Creates a new customers")
    @ApiResponse(responseCode = "201", description = "Customer saved success")
    @ApiResponse(responseCode = "409", description = "Customer already registered")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<CustomerResponse> saveCustomer(
            @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.addCustomer(request));
    }

    @GetMapping
    @Operation(summary = "List customers", description = "Returns all registered")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.filterAllCustomers(token));
    }

    @GetMapping("/current")
    @Operation(summary = "Find user by username", description = "Fetches a user by their username")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<CustomerResponse> findByCurrentUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.filterCurrentUser(token));
    }

    @PutMapping
    @Operation(summary = "Update customer", description = "Edits customer information")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "409", description = "Customer already updated")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestHeader("Authorization") String token,
            @RequestBody CustomerRequest request
    ) {
        return ResponseEntity.ok(customerService.editCustomer(token, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Removes a user by ID")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "404", description = "Customer already removed")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<CustomerResponse> deleteCustomer(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(customerService.removeCustomer(token, id));
    }
}
