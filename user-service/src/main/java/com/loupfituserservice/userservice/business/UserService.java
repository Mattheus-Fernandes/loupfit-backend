package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.UserConverter;
import com.loupfituserservice.userservice.business.dto.UserCreateDTO;
import com.loupfituserservice.userservice.business.dto.UserDTO;
import com.loupfituserservice.userservice.business.dto.UserLoginDTO;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictExcpetion;
import com.loupfituserservice.userservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO addUser(UserCreateDTO userCreateDTO) {
        existUsername(userCreateDTO.getUsername());
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        User newUser = userConverter.userCreate(userCreateDTO);

        return userConverter.userDTO(userRepository.save(newUser));

    }

    public void existUsername(String username) {
       try {
           boolean exist = userRepository.existsByUsername(username);

           if (exist) {
               throw new ConflictExcpetion("Usuário já cadastrado " + username);
           }
       } catch (ConflictExcpetion e ) {
           throw new ConflictExcpetion(e.getMessage());
       }
    }

}
