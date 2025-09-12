package com.loupfitassetservice.asset_service.infrastructure.client;

import com.loupfitassetservice.asset_service.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user")
    UserDTO findUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);
}
