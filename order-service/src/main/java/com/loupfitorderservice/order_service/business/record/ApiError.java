package com.loupfitorderservice.order_service.business.record;

public record ApiError(
        String msg,
        int status
) { }
