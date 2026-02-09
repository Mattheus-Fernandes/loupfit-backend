package com.loupfit.bffservice.infrastructure.exceptions;

import com.loupfit.bffservice.business.record.error.ApiError;

public class ForbiddenException extends RuntimeException {

    private final ApiError apiError;

    public ForbiddenException(ApiError apiError) {
        super(apiError.msg());
        this.apiError = apiError;
    }
}
