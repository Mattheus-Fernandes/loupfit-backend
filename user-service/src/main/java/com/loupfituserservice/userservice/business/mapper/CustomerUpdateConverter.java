package com.loupfituserservice.userservice.business.mapper;

import com.loupfituserservice.userservice.business.record.customer.in.CustomerRequest;
import com.loupfituserservice.userservice.infrastructure.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerUpdateConverter {

    Customer doUpdate(CustomerRequest request, @MappingTarget Customer entity);
}
