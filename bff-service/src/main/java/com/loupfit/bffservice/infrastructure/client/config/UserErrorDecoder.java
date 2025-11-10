package com.loupfit.bffservice.infrastructure.client.config;

import com.loupfit.bffservice.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

public class UserErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {

            case 409:
                return new ConflictException("Usuário já cadastrado");

            case 404:
                return new ResourceNotFoundException("Usuário não encontrado");

            case 403:
                return new ForbiddenException("Sem permissão, acesso negado");

            case 401:
                return new UnauthorizedException("Usuário ou senha inválidos");

            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
