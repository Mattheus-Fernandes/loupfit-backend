package com.loupfitassetservice.asset_service.infrastructure.security;


import com.loupfitassetservice.asset_service.business.dto.UserDTO;
import com.loupfitassetservice.asset_service.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails dataUser(String token, String username) {
        String bearerToken = "Bearer " + token;
        UserDTO userDTO = userClient.findUserByUsername(bearerToken, username);

        return User
                .withUsername(userDTO.getUsername())
                .password("N/A")
                .build();
    }
}
