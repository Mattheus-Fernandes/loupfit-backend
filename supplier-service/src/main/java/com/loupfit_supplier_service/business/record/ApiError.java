package com.loupfit_supplier_service.business.record;

public record ApiError(
        String msg,
        int status
) { }
