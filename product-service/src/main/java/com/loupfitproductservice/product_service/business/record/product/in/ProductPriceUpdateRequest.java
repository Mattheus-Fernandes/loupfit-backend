package com.loupfitproductservice.product_service.business.record.product.in;

import java.math.BigDecimal;

public record ProductPriceUpdateRequest(
        BigDecimal price
) {
}
