package com.loupfituserservice.userservice.business;

import com.loupfituserservice.userservice.business.record.user.in.*;
import com.loupfituserservice.userservice.business.record.user.out.UserResponse;
import com.loupfituserservice.userservice.business.mapper.UserConverter;
import com.loupfituserservice.userservice.business.mapper.UserUpdateConverter;
import com.loupfituserservice.userservice.infrastructure.entity.User;
import com.loupfituserservice.userservice.infrastructure.enums.UserRole;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ForbiddenException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ResourceNotFoundException;
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
    private final UserUpdateConverter userUpdateConverter;
    private final PasswordEncoder passwordEncoder;

    private User userLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String user = auth.getName();

        return userRepository.findByUsername(user).orElseThrow(
                () -> new ResourceNotFoundException("Usuário logado não encontrado")
        );
    }

    private void userValidation(String action) {
        User user = userLogged();

        if (user.getRole().equals(UserRole.VIEWER)) {
            throw new ForbiddenException("Você não tem permissão para " + action + " usuário");
        }
    }

    public UserResponse addUser(UserRequest request) {
        existUsername(request.username());

        String password = request.password() != null ? passwordEncoder.encode(request.password()) : null;

        UserRequest data = new UserRequest(
                request.name(),
                request.lastname(),
                request.username(),
                password,
                request.role()
        );

        User user = userConverter.toEntity(data);

        return userConverter.toResponse(userRepository.save(user));
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

    public List<UserResponse> filterAllUsers() {
        List<User> userList = userRepository.findAll();

        return userConverter.toResponseList(userList);
    }

    public UserResponse filterByUsername(String username) {
        try {
            return userConverter.toResponse(
                    userRepository.findByUsername(username).orElseThrow(
                            () -> new ResourceNotFoundException("Usuário não encontrado " + username)
                    )
            );

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public UserResponse removeUser(Long id) {

        userValidation("excluir");

        User userDelete = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        userRepository.deleteById(id);

        return userConverter.toResponse(userDelete);
    }

    public UserResponse editUser(Long id, UserRequest request) {

        userValidation("editar");

        String password = request.password() != null ? passwordEncoder.encode(request.password()) : null;

        UserRequest data = new UserRequest(
                request.name(),
                request.lastname(),
                request.username(),
                password,
                request.role()
        );

        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        User editUser = userUpdateConverter.doUpdate(data, userEntity);

        return userConverter.toResponse(userRepository.save(editUser));

    }

    public UserResponse editRoleUser(Long id, UserRoleRequest request) {

        userValidation("editar");

        User entity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        if (request.role() != null) {
            entity.setRole(request.role());
        }

        return userConverter.toResponse(userRepository.save(entity));
    }

    public UserResponse editUsername(Long id, UsernameRequest request) {

        userValidation("editar");

        User entity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        if (request.username() != null) {
            entity.setUsername(request.username());
        }

        return userConverter.toResponse(userRepository.save(entity));

    }

}
