package com.loupfitorderservice.order_service.business.record.product.out;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String imageUrl,
        BigDecimal price,
        BigDecimal costPrice,
        Integer stock,
        String category,
        String subcategory,
        String size,
        String color,
        String material,
        Integer sales,
        String createdBy
) {
}
