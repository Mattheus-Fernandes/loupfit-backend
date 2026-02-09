package com.loupfit.bffservice.infrastructure.exceptions;

import com.loupfit.bffservice.business.record.error.ApiError;

public class UnauthorizedException extends RuntimeException {

    private final ApiError apiError;


    public UnauthorizedException(ApiError apiError) {
        super(apiError.msg());
        this.apiError = apiError;
    }


}
