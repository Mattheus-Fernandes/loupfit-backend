package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.AuthService;
import com.loupfit.bffservice.business.dto.in.LoginReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Login of users and customer")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    @Operation(summary = "Login", description = "Authenticates user or customer")
    @ApiResponse(responseCode = "200", description = "Logged successfully")
    @ApiResponse(responseCode = "401", description = "Credentials invalid")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Error server")
    public String doLogin(
            @RequestBody LoginReqDTO dto
    ) {
        return authService.doLogin(dto);
    }
}
