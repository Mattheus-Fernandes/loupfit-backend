package com.loupfit.bffservice.infrastructure.exceptions;

import com.loupfit.bffservice.business.record.error.ApiError;

public class BusinessException extends RuntimeException {

    private ApiError apiError;

    public BusinessException(ApiError apiError) {
        super(apiError.msg());
        this.apiError = apiError;
    }

}
