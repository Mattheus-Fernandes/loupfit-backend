package com.loupfit.bffservice.infrastructure.client.config;

import com.loupfit.bffservice.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

public class AssetErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {

            case 409:
                return new ConflictException("Equipamento já cadastrado");

            case 404:
                return new ResourceNotFoundException("Equipamento não encontrado");

            case 403:
                return new ForbiddenException("Sem permissão, acesso negado");

            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
