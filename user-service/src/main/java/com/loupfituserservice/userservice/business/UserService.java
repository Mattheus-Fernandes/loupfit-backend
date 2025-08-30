package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.converter.UserConverter;
import com.loupfituserservice.userservice.business.dto.UserCreateDTO;
import com.loupfituserservice.userservice.business.dto.UserDTO;
import com.loupfituserservice.userservice.business.dto.UserEditDTO;
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
        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public List<UserDTO> filterAllUsers() {
        List<User> userList = userRepository.findAll();

        return userConverter.userDTOList(userList);
    }

    public List<UserDTO> filterByRole(Long role) {
        Long value = role == 1L ? 1L : 2L;
        List<User> userList = userRepository.findByRole(value);

        if (userList.isEmpty()) {
            throw new ConflictExcpetion("Nenhum usuário encontrado");
        }

        return userConverter.userDTOList(userList);

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

    public UserDTO editUser(Long id, UserEditDTO userEditDTO) {

        User user = userLogged();

        if (!user.getRole().equals(1L)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar usuário.");
        }

        userEditDTO.setPassword(userEditDTO.getPassword() != null ? passwordEncoder.encode(userEditDTO.getPassword()) : null);

        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Usuário não encontrado")
        );

        User editUser = userConverter.userUpdate(userEditDTO, userEntity);

        return userConverter.userDTO(userRepository.save(editUser));

    }

}
