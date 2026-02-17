package com.loupfitconsumablesservice.consumables_service.business.record.consumable.in;

public record ConsumableQuantityRequest(
    Integer quantity,
    String operation
) { }
