package com.loupfitorderservice.order_service.business.record.product.in;

public record ProductUpdateStockSalesRequest(
        Integer quantity,
        String operation,
        String inventory
) {
}
