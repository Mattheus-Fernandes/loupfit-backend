package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.LoginRequest;
import com.loupfit.bffservice.business.record.error.ApiError;
import com.loupfit.bffservice.infrastructure.client.AuthClient;
import com.loupfit.bffservice.infrastructure.exceptions.ForbiddenException;
import com.loupfit.bffservice.infrastructure.exceptions.UnauthorizedException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthClient authClient;

    public String doLogin(LoginRequest request) {

        try {
            return authClient.doLogin(request);
        } catch (FeignException.Unauthorized e) {
            throw new UnauthorizedException(
                    new ApiError(
                            "Usuário ou senha incorretos",
                            HttpStatus.UNAUTHORIZED.value()
                    )
            );
        } catch (FeignException.Forbidden e) {
            throw new ForbiddenException(
                    new ApiError(
                            "Acesso negado, você não tem permissão para acessar esse recurso",
                            HttpStatus.FORBIDDEN.value()
                    )
            );
        } catch (FeignException e) {
            throw new RuntimeException("Erro ao tentar se autenticar", e);
        }
    }
}
