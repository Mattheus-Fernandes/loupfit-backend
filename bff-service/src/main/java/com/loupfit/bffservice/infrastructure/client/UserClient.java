package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.out.UserDTO;
import com.loupfit.bffservice.business.dto.in.UserReqDTO;
import com.loupfit.bffservice.business.dto.in.UserRoleDTO;
import com.loupfit.bffservice.business.dto.in.UsernameReqDTO;
import com.loupfit.bffservice.infrastructure.client.config.UserClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-one", url = "${user.url}", configuration = UserClientConfig.class)
public interface UserClient {

    @GetMapping("/user/search")
    UserDTO getUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);

    @PostMapping("/user")
    UserDTO saveUser(@RequestBody UserReqDTO newUser);

    @GetMapping("/user")
    List<UserDTO> findUser(@RequestHeader("Authorization") String token);

    @DeleteMapping("/user/{id}")
    UserDTO deleteUser(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @PutMapping("/user/{id}")
    UserDTO updateUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserReqDTO userEditDTO
    );

    @PatchMapping("/user/{id}/role")
    UserDTO updateRoleUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UserRoleDTO dto
    );

    @PatchMapping("/user/{id}/username")
    UserDTO updateUsernameUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody UsernameReqDTO dto);
}
