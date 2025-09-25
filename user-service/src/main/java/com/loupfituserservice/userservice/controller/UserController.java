package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.UserService;
import com.loupfituserservice.userservice.business.dto.user.UserReqDTO;
import com.loupfituserservice.userservice.business.dto.user.UserDTO;
import com.loupfituserservice.userservice.business.dto.LoginDTO;
import com.loupfituserservice.userservice.business.dto.user.UserRoleDTO;
import com.loupfituserservice.userservice.business.dto.user.UsernameDTO;
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

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserReqDTO newUser) {
        return ResponseEntity.ok(userService.addUser(newUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findUser() {
        return ResponseEntity.ok(userService.filterAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<UserDTO> findUserByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.filterByUsername(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.removeUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserReqDTO userEditDTO
    ) {

        return ResponseEntity.ok(userService.editUser(id, userEditDTO));
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UserDTO> updateRoleUser(
            @PathVariable Long id,
            @RequestBody UserRoleDTO dto
    ) {
        return ResponseEntity.ok(userService.editRoleUser(id, dto));
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<UserDTO> updateUsernameUser(
            @PathVariable Long id,
            @RequestBody UsernameDTO dto
    ) {
        return ResponseEntity.ok(userService.editUsername(id, dto));
    }


}
