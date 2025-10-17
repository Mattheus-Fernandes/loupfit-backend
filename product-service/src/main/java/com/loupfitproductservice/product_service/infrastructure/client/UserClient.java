package com.loupfitproductservice.product_service.infrastructure.client;

import com.loupfitproductservice.product_service.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user/search")
    UserDTO getUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);
}
