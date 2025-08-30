package com.loupfituserservice.userservice.business.converter;

import com.loupfituserservice.userservice.business.dto.UserCreateDTO;
import com.loupfituserservice.userservice.business.dto.UserDTO;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConverter {

    public User userCreate(UserCreateDTO userCreateDTO) {
        return User.builder()
                .name(userCreateDTO.getName())
                .lastname(userCreateDTO.getLastname())
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .role(userCreateDTO.getRole())
                .build();
    }

    public UserDTO userDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

}
