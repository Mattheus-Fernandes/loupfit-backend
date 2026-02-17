package com.loupfitassetservice.asset_service.infrastructure.security;


import com.loupfitassetservice.asset_service.business.record.user.out.UserResponse;
import com.loupfitassetservice.asset_service.infrastructure.client.UserClient;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
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
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }
}
