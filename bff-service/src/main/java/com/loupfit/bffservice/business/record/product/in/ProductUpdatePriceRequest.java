package com.loupfit.bffservice.business.record.product.in;

import java.math.BigDecimal;

public record ProductUpdatePriceRequest(
        BigDecimal price
) {
}
