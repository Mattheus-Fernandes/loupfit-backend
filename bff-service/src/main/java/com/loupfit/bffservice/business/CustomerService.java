package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.out.CustomerDTO;
import com.loupfit.bffservice.business.dto.in.CustomerReqDTO;
import com.loupfit.bffservice.infrastructure.client.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    public CustomerDTO addCustomer(@RequestBody CustomerReqDTO dto) {
        return customerClient.saveCustomer(dto);

    }

    public List<CustomerDTO> filterAllCustomers(String token) {
        return customerClient.findAllCustomers(token);
    }

    public CustomerDTO filterCurrentUser(String token) {
       return customerClient.findByCurrentUser(token);
    }

    public CustomerDTO editCustomer(String token, CustomerReqDTO dto) {
        return customerClient.updateCustomer(token, dto);
    }

    public CustomerDTO removeCustomer(String token, Long id) {
        return customerClient.deleteCustomer(token, id);
    }
}
