package com.loupfit.bffservice.infrastructure.client.config;

import com.loupfit.bffservice.infrastructure.exceptions.BusinessException;
import com.loupfit.bffservice.infrastructure.exceptions.ConflictException;
import com.loupfit.bffservice.infrastructure.exceptions.ForbiddenException;
import com.loupfit.bffservice.infrastructure.exceptions.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ProductErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {

            case 409:
                return new ConflictException("Produto já cadastrado");

            case 404:
                return new ResourceNotFoundException("Produto não encontrado");

            case 403:
                return new ForbiddenException("Sem permissão, acesso negado");

            case 415:
                return new ConflictException("Formato de imagem ou arquivo não suportado, tente novamente!");

            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
