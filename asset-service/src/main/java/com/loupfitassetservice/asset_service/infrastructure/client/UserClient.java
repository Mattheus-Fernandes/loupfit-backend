package com.loupfitassetservice.asset_service.infrastructure.client;

import com.loupfitassetservice.asset_service.business.dto.EmployeeDTO;
import com.loupfitassetservice.asset_service.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user/search")
    UserDTO getUserByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);

    @GetMapping("/employee/search")
    EmployeeDTO getEmployeeByUsername(@RequestHeader("Authorization") String token, @RequestParam("username") String username);

}
