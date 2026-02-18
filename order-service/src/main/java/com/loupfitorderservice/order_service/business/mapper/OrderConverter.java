package com.loupfitorderservice.order_service.business.mapper;

import com.loupfitorderservice.order_service.business.record.order.in.OrderRequest;
import com.loupfitorderservice.order_service.business.record.order.out.OrderResponse;
import com.loupfitorderservice.order_service.infrastructure.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    Order toEntity(OrderRequest request);

    OrderResponse toResponse(Order entity);

    List<OrderResponse> toResponseList(List<Order> entities);
}
