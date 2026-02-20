package com.loupfitconsumablesservice.consumables_service.business.record.consumable.out;

import java.math.BigDecimal;

public record ConsumableResponse(
        String id,
        String name,
        String description,
        BigDecimal costValue,
        Integer quantity,
        String placePurchase,
        String purchaseLink,
        String createdBy
) {
}
