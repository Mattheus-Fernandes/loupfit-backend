package com.loupfitconsumablesservice.consumables_service.infrastructure.security;


import com.loupfitconsumablesservice.consumables_service.business.record.user.out.UserResponse;
import com.loupfitconsumablesservice.consumables_service.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails dataUser(String token, String username) {
        String bearerToken = "Bearer " + token;
        UserResponse user = userClient.findUserByUsername(bearerToken, username);

        return User
                .withUsername(user.username())
                .password("N/A")
                .build();
    }
}
