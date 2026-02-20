package com.loupfit.bffservice.business.record.consumable.in;

public record ConsumableQuantityRequest(
        Integer quantity,
        String operation
) {
}
