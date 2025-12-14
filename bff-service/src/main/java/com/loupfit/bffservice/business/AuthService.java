package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.in.LoginReqDTO;
import com.loupfit.bffservice.infrastructure.client.AuthClient;
import com.loupfit.bffservice.infrastructure.exceptions.ForbiddenException;
import com.loupfit.bffservice.infrastructure.exceptions.UnauthorizedException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthClient authClient;

    public String doLogin(LoginReqDTO dto) {

        try {
            return authClient.doLogin(dto);
        } catch (FeignException.Unauthorized e) {
            throw new UnauthorizedException("Usuário ou senha incorretos");
        } catch (FeignException.Forbidden e) {
            throw new ForbiddenException("Acesso negado, você não tem permissão para acessar esse recurso", e.getCause());
        } catch (FeignException e) {
            throw new RuntimeException("Erro ao tentar se autenticar", e);
        }

    }
}
