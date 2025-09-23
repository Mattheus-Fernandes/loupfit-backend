package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.UserConverter;
import com.loupfituserservice.userservice.business.dto.user.UserReqDTO;
import com.loupfituserservice.userservice.business.dto.user.UserDTO;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictExcpetion;
import com.loupfituserservice.userservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    private User userLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String user = authentication.getName();

        return userRepository.findByUsername(user).orElseThrow(
                () -> new ConflictExcpetion("Usuário logado não encontrado")
        );
    }

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
                throw new ConflictExcpetion("Usuário(a) já cadastrado(a) " + username);
            }
        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
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
                            () -> new ConflictExcpetion("Usuário não encontrado " + username)
                    )
            );

        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public UserDTO removeUser(Long id) {

        User user = userLogged();

        if (!user.getRole().equals(1L)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para excluir usuário.");
        }

        User userDelete = userRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        userRepository.deleteById(id);

        return userConverter.userDTO(userDelete);
    }

    public UserDTO editUser(Long id, UserReqDTO dto) {

        User user = userLogged();

        if (!user.getRole().equals(1L)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar usuário.");
        }

        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        User editUser = userConverter.userUpdate(dto, userEntity);

        return userConverter.userDTO(userRepository.save(editUser));

    }

}
