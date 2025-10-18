package com.loupfitorderservice.order_service.business.mapper;

import com.loupfitorderservice.order_service.business.dto.OrderDTO;
import com.loupfitorderservice.order_service.infrastructure.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    Order orderEntity(OrderDTO dto);

    OrderDTO orderDTO(Order entity);
}
