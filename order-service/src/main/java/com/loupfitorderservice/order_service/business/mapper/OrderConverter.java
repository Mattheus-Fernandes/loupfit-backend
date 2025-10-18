package com.loupfitorderservice.order_service.business.mapper;

import com.loupfitorderservice.order_service.infrastructure.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    Order
}
