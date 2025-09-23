package com.loupfituserservice.userservice.business.converter;

import com.loupfituserservice.userservice.business.dto.user.UserReqDTO;
import com.loupfituserservice.userservice.business.dto.user.UserDTO;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserConverter {

    public User userCreate(UserReqDTO dto) {
        return User.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

    public UserDTO userDTO(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

    public List<UserDTO> userDTOList(List<User> entities) {

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        for (User user : entities) {
            userDTOList.add(userDTO(user));
        }

        return userDTOList;
    }

    public User userUpdate(UserReqDTO dto, User entity) {
        return User.builder()
                .id(entity.getId())
                .name(dto.getName() != null ? dto.getName() : entity.getName())
                .lastname(dto.getLastname() != null ? dto.getLastname() : entity.getLastname())
                .username(dto.getUsername() != null ? dto.getUsername() : entity.getUsername())
                .password(dto.getPassword() != null ? dto.getPassword() : entity.getPassword())
                .role(dto.getRole() != null ? dto.getRole() : entity.getRole())
                .build();
    }

}
