package com.loupfitorderservice.order_service.infrastructure.security;


import com.loupfitorderservice.order_service.business.dto.user.UserDTO;
import com.loupfitorderservice.order_service.infrastructure.security.client.UserClient;
import com.loupfitorderservice.order_service.infrastructure.exceptions.ConflictExcpetion;
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
            UserDTO userDTO = userClient.getUserByUsername(bearerToken, username);
            return User.withUsername(userDTO.getUsername())
                    .password("N/A")
                    .roles(userDTO.getRole().name())
                    .build();
        } catch (UsernameNotFoundException e) {
            throw new ConflictExcpetion("Usuário não encontrado");
        }
    }
}
