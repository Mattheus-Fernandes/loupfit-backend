package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.out.CustomerDTO;
import com.loupfit.bffservice.business.dto.in.CustomerReqDTO;
import com.loupfit.bffservice.infrastructure.client.config.UserClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-two", url = "${user.url}", configuration = UserClientConfig.class)
public interface CustomerClient {

    @PostMapping("/customer")
    CustomerDTO saveCustomer(@RequestBody CustomerReqDTO dto);

    @GetMapping("/customer")
    List<CustomerDTO> findAllCustomers(@RequestHeader("Authorization") String token);

    @GetMapping("/customer/current")
    CustomerDTO findByCurrentUser(@RequestHeader("Authorization") String token);

    @PutMapping("/customer")
    CustomerDTO updateCustomer(@RequestHeader("Authorization") String token, @RequestBody CustomerReqDTO dto);

    @DeleteMapping("/customer/{id}")
    CustomerDTO deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
