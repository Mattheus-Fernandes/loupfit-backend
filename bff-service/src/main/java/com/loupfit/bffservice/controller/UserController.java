package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.UserService;
import com.loupfit.bffservice.business.dto.out.UserDTO;
import com.loupfit.bffservice.business.dto.in.UserReqDTO;
import com.loupfit.bffservice.business.dto.in.UserRoleDTO;
import com.loupfit.bffservice.business.dto.in.UsernameReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Registers and searches of users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Register user", description = "Creates a new user account.")
    @ApiResponse(responseCode = "200", description = "User saved success")
    @ApiResponse(responseCode = "409", description = "User already registered")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserReqDTO newUser) {
        return ResponseEntity.ok(userService.addUser(newUser));
    }

    @GetMapping
    @Operation(summary = "List users", description = "Returns all registered users.")
    @ApiResponse(responseCode = "200", description = "Users found")
    @ApiResponse(responseCode = "404", description = "Users not found")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<List<UserDTO>> findUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.filterAllUsers(token));
    }

    @GetMapping("/search")
    @Operation(summary = "Find user by username", description = "Fetches a user by their username.")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<UserDTO> findUserByUsername(
            @RequestHeader("Authorization") String token,
            @RequestParam("username") String username
    ) {
        return ResponseEntity.ok(userService.filterByUsername(token, username));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Removers a user by ID.")
    @ApiResponse(responseCode = "200", description = "User removed successfully")
    @ApiResponse(responseCode = "404", description = "User already removed")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<UserDTO> deleteUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.removeUser(token, id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Edits user information.")
    @ApiResponse(responseCode = "201", description = "Edit updated successfully")
    @ApiResponse(responseCode = "409", description = "User already updated")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<UserDTO> updateUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserReqDTO userEditDTO
    ) {

        return ResponseEntity.ok(userService.editUser(token, id, userEditDTO));
    }

    @PatchMapping("/{id}/role")
    @Operation(summary = "Update user role", description = "Changes the user role.")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "409", description = "User already updated")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<UserDTO> updateRoleUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserRoleDTO dto
    ) {
        return ResponseEntity.ok(userService.editRoleUser(token, id, dto));
    }

    @PatchMapping("/{id}/username")
    @Operation(summary = "Update username", description = "Changes the users username.")
    @ApiResponse(responseCode = "200", description = "Edit updated successfully")
    @ApiResponse(responseCode = "409", description = "User already updated")
    @ApiResponse(responseCode = "500", description = "Error server")
    public ResponseEntity<UserDTO> updateUsernameUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UsernameReqDTO dto) {
        return ResponseEntity.ok(userService.editUsername(token, id, dto));
    }


}