package com.loupfit_supplier_service.infrastructure.security;


import com.loupfit_supplier_service.business.record.user.out.UserResponse;
import com.loupfit_supplier_service.infrastructure.client.UserClient;
import com.loupfit_supplier_service.infrastructure.exceptions.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails dataUser(String token, String username) {
        String bearerToken = "Bearer " + token;

        try {
            UserResponse user = userClient.getUserByUsername(bearerToken, username);
            return User.withUsername(user.username())
                    .password("N/A")
                    .roles(user.role().name())
                    .build();
        } catch (UsernameNotFoundException e) {
            throw new ConflictException("Usuário não encontrado");
        }
    }
}
