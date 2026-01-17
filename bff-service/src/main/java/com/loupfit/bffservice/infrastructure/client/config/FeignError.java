package com.loupfit.bffservice.infrastructure.client.config;

import com.loupfit.bffservice.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String errorMsg = errorMsg(response);

        switch (response.status()) {
            case 415, 409:
                return new ConflictException(errorMsg);
            case 404:
                return new ResourceNotFoundException(errorMsg);
            case 403:
                return new ForbiddenException(errorMsg);
            case 401:
                return new UnauthorizedException(errorMsg);
            default:
                return new BusinessException(errorMsg);
        }
    }

    private String errorMsg(Response response) {
        try {

            if (Objects.isNull(response.body())) {
                return "";
            }

            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
