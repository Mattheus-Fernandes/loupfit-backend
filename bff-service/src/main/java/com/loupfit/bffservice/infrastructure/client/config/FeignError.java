package com.loupfit.bffservice.infrastructure.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loupfit.bffservice.business.record.error.ApiError;
import com.loupfit.bffservice.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.nio.charset.StandardCharsets;

public class FeignError implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {

        ApiError apiError = parseError(response);

        switch (response.status()) {
            case 415, 409:
                return new ConflictException(apiError);
            case 404:
                return new ResourceNotFoundException(apiError);
            case 403:
                return new ForbiddenException(apiError);
            case 401:
                return new UnauthorizedException(apiError);
            default:
                return new BusinessException(apiError);
        }
    }

    private ApiError parseError(Response response) {
        try {

            if (response.body() == null) {
                return new ApiError("Erro desconhecido", response.status());
            }

            String json = new String(
                    response.body().asInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );

            return mapper.readValue(json, ApiError.class);

        } catch (Exception e) {
            return new ApiError("Erro ao processar respostas", response.status());
        }
    }
}
