package com.loupfitassetservice.asset_service.infrastructure.security;


import com.loupfitassetservice.asset_service.business.dto.EmployeeDTO;
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
        try {
            UserDTO userDTO = userClient.getUserByUsername(bearerToken, username);
            return User.withUsername(userDTO.getUsername())
                    .password("N/A")
                    .roles(userDTO.getRole().name())
                    .build();
        } catch (Exception e) {
            EmployeeDTO employeeDTO = userClient.getEmployeeByUsername(bearerToken, username);
            return User.withUsername(employeeDTO.getUsername())
                    .password("N/A")
                    .roles(employeeDTO.getRole().name())
                    .build();
        }
    }
}
