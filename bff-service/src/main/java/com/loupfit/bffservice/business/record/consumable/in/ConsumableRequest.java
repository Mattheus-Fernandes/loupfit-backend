package com.loupfit.bffservice.business.record.consumable.in;

import java.math.BigDecimal;

public record ConsumableRequest(
        String name,
        String description,
        BigDecimal costValue,
        Integer quantity,
        String placePurchase,
        String purchaseLink,
        String createdBy
) {
}
