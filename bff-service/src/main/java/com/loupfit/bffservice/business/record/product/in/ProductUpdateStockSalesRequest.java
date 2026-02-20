package com.loupfit.bffservice.business.record.product.in;

public record ProductUpdateStockSalesRequest(
        Integer quantity,
        String operation,
        String inventory
) {
}
