package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.UserService;
import com.loupfituserservice.userservice.business.dto.UserCreateDTO;
import com.loupfituserservice.userservice.business.dto.UserDTO;
import com.loupfituserservice.userservice.business.dto.UserLoginDTO;
import com.loupfituserservice.userservice.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCreateDTO newUser) {
        return ResponseEntity.ok(userService.addUser(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody UserLoginDTO userLogin) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
            );

            String token = jwtUtil.generateToken(authentication.getName());

            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findUser(@RequestParam(required = false) Long role) {

        if (role == null) {
            return ResponseEntity.ok(userService.filterAllUsers());

        }

        return ResponseEntity.ok(userService.filterByRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.removeUser(id));
    }
}
