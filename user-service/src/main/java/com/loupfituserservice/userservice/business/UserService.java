package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.UserConverter;
import com.loupfituserservice.userservice.business.dto.user.UserReqDTO;
import com.loupfituserservice.userservice.business.dto.user.UserDTO;
import com.loupfituserservice.userservice.business.dto.user.UserRoleDTO;
import com.loupfituserservice.userservice.business.dto.user.UsernameDTO;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfituserservice.userservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO addUser(UserReqDTO dto) {
        existUsername(dto.getUsername());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        User newUser = userConverter.userCreate(dto);

        return userConverter.userDTO(userRepository.save(newUser));
    }

    public void existUsername(String username) {
        try {
            boolean exist = userRepository.existsByUsername(username);

            if (exist) {
                throw new ConflictException("Usuário(a) já cadastrado(a) " + username);
            }
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public List<UserDTO> filterAllUsers() {
        List<User> userList = userRepository.findAll();

        return userConverter.userDTOList(userList);
    }

    public UserDTO filterByUsername(String username) {
        try {
            return userConverter.userDTO(
                    userRepository.findByUsername(username).orElseThrow(
                            () -> new ResourceNotFoundException("Usuário não encontrado " + username)
                    )
            );

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public UserDTO removeUser(Long id) {

        User userDelete = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        userRepository.deleteById(id);

        return userConverter.userDTO(userDelete);
    }

    public UserDTO editUser(Long id, UserReqDTO dto) {

        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        User editUser = userConverter.userUpdate(dto, userEntity);

        return userConverter.userDTO(userRepository.save(editUser));

    }

    public UserDTO editRoleUser(Long id, UserRoleDTO dto) {

        User entity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        }

        return userConverter.userDTO(userRepository.save(entity));
    }

    public UserDTO editUsername(Long id, UsernameDTO dto) {

        User entity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        if (dto.getUsername() != null) {
            entity.setUsername(dto.getUsername());
        }

        return userConverter.userDTO(userRepository.save(entity));

    }

}
