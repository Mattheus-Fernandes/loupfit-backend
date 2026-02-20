package com.loupfit.bffservice.business.record.product.out;

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
        String material,
        Integer sales,
        String createdBy
) {
}
