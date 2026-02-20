package com.loupfitproductservice.product_service.business.record;

public record ApiError(
        String msg,
        int status
) { }
