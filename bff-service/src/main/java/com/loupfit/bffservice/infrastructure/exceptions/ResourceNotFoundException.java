package com.loupfit.bffservice.infrastructure.exceptions;

import com.loupfit.bffservice.business.record.error.ApiError;

public class ResourceNotFoundException extends RuntimeException {

    private final ApiError apiError;

    public ResourceNotFoundException(ApiError apiError) {
        super(apiError.msg());
        this.apiError = apiError;
    }

}
