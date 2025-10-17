package com.loupfitorderservice.order_service.infrastructure.security.client;

import com.loupfitorderservice.order_service.business.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user/search")
    UserDTO getUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);
}
