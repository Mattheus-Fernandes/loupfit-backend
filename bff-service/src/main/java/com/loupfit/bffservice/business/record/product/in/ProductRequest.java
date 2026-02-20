package com.loupfit.bffservice.business.record.product.in;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        String imageUrl,
        BigDecimal price,
        BigDecimal costPrice,
        Integer stock,
        String category,
        String subcategory,
        String size,
        String material,
        Integer sales,
        String createdBy
) {
}
