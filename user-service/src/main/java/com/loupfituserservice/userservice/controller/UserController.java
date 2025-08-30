package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.UserService;
import com.loupfituserservice.userservice.business.dto.UserCreateDTO;
import com.loupfituserservice.userservice.business.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCreateDTO newUser) {
        return ResponseEntity.ok(userService.addUser(newUser));
    }
}
