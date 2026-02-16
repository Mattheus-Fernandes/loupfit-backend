package com.loupfituserservice.userservice.controller;

import com.loupfituserservice.userservice.business.UserService;
import com.loupfituserservice.userservice.business.record.user.in.*;
import com.loupfituserservice.userservice.business.record.user.out.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest newUser) {
        return ResponseEntity.ok(userService.addUser(newUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findUser() {
        return ResponseEntity.ok(userService.filterAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<UserResponse> findUserByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.filterByUsername(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.removeUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ) {

        return ResponseEntity.ok(userService.editUser(id, request));
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UserResponse> updateRoleUser(
            @PathVariable Long id,
            @RequestBody UserRoleRequest request
    ) {
        return ResponseEntity.ok(userService.editRoleUser(id, request));
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<UserResponse> updateUsernameUser(
            @PathVariable Long id,
            @RequestBody UsernameRequest request
    ) {
        return ResponseEntity.ok(userService.editUsername(id, request));
    }

}
