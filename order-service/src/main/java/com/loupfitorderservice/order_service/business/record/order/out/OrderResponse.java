package com.loupfitorderservice.order_service.business.record.order.out;

import java.math.BigDecimal;

public record OrderResponse(
        String id,
        String orderId,
        Long productId,
        String productName,
        String imageUrl,
        Integer quantity,
        BigDecimal totalPrice,
        String size,
        String color,
        String soldBy,
        String paymentMethod
) {
}
