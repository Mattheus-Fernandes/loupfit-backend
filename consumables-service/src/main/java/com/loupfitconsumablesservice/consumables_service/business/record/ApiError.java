package com.loupfitconsumablesservice.consumables_service.business.record;

public record ApiError(
        String msg,
        int status
) { }
