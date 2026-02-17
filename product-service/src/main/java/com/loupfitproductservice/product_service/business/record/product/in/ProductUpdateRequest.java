package com.loupfitproductservice.product_service.business.record.product.in;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        Long id,
        String name,
        String description,
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
