package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.customer.in.CustomerRequest;
import com.loupfit.bffservice.business.record.customer.out.CustomerResponse;
import com.loupfit.bffservice.infrastructure.client.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    public CustomerResponse addCustomer(@RequestBody CustomerRequest request) {
        return customerClient.saveCustomer(request);
    }

    public List<CustomerResponse> filterAllCustomers(String token) {
        return customerClient.findAllCustomers(token);
    }

    public CustomerResponse filterCurrentUser(String token) {
       return customerClient.findByCurrentUser(token);
    }

    public CustomerResponse editCustomer(String token, CustomerRequest request) {
        return customerClient.updateCustomer(token, request);
    }

    public CustomerResponse removeCustomer(String token, Long id) {
        return customerClient.deleteCustomer(token, id);
    }
}
