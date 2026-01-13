package com.loupfituserservice.userservice.business.mapper;

import com.loupfituserservice.userservice.business.record.customer.in.CustomerRequest;
import com.loupfituserservice.userservice.business.record.customer.out.CustomerResponse;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerConverter {

    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer entity);

    List<CustomerResponse> toResponseList(List<Customer> entities);
}
