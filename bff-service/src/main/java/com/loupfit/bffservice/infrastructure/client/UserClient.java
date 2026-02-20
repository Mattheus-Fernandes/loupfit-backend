package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.user.in.UserRequest;
import com.loupfit.bffservice.business.record.user.in.UserRoleRequest;
import com.loupfit.bffservice.business.record.user.in.UsernameRequest;
import com.loupfit.bffservice.business.record.user.out.UserResponse;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-one", url = "${user.url}", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/users/search")
    UserResponse getUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);

    @PostMapping("/users")
    UserResponse saveUser(@RequestBody UserRequest request);

    @GetMapping("/users")
    List<UserResponse> findUser(@RequestHeader("Authorization") String token);

    @DeleteMapping("/users/{id}")
    UserResponse deleteUser(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @PutMapping("/users/{id}")
    UserResponse updateUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserRequest request
    );

    @PatchMapping("/users/{id}/role")
    UserResponse updateRoleUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserRoleRequest request
    );

    @PatchMapping("/users/{id}/username")
    UserResponse updateUsernameUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UsernameRequest request);
}
