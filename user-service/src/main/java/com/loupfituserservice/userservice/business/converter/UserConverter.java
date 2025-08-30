package com.loupfituserservice.userservice.business.converter;

import com.loupfituserservice.userservice.business.dto.UserCreateDTO;
import com.loupfituserservice.userservice.business.dto.UserDTO;
import com.loupfituserservice.userservice.business.dto.UserEditDTO;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserDTO> userDTOList(List<User> users) {

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        for (User user : users) {
            userDTOList.add(userDTO(user));
        }

        return userDTOList;
    }

    public User userUpdate(UserEditDTO userEditDTO, User user) {
        return User.builder()
                .id(user.getId())
                .name(userEditDTO.getName() != null ? userEditDTO.getName() : user.getName())
                .lastname(userEditDTO.getLastname() != null ? userEditDTO.getLastname() : user.getLastname())
                .username(userEditDTO.getUsername() != null ? userEditDTO.getUsername() : user.getUsername())
                .password(userEditDTO.getPassword() != null ? userEditDTO.getPassword() : user.getPassword())
                .role(userEditDTO.getRole() != null ? userEditDTO.getRole() : user.getRole())
                .build();
    }

}
