package com.loupfitorderservice.order_service.business.record.order.in;

import java.math.BigDecimal;

public record OrderRequest(
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
