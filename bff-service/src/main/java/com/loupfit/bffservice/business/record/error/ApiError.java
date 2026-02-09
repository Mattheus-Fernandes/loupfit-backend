package com.loupfit.bffservice.business.record.error;

public record ApiError(
        String msg,
        int status
) { }
