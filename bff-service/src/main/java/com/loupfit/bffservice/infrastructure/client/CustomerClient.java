package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.customer.in.CustomerRequest;
import com.loupfit.bffservice.business.record.customer.out.CustomerResponse;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-two", url = "${user.url}", configuration = FeignConfig.class)
public interface CustomerClient {

    @PostMapping("/customers")
    CustomerResponse saveCustomer(@RequestBody CustomerRequest request);

    @GetMapping("/customers")
    List<CustomerResponse> findAllCustomers(@RequestHeader("Authorization") String token);

    @GetMapping("/customers/current")
    CustomerResponse findByCurrentUser(@RequestHeader("Authorization") String token);

    @PutMapping("/customers")
    CustomerResponse updateCustomer(@RequestHeader("Authorization") String token, @RequestBody CustomerRequest request);

    @DeleteMapping("/customers/{id}")
    CustomerResponse deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
