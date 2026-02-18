package com.loupfitorderservice.order_service.infrastructure.security.client;

import com.loupfitorderservice.order_service.business.record.user.out.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${user.url}")
public interface UserClient {

    @GetMapping("/users/search")
    UserResponse getUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);
}
