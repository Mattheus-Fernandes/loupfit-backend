package com.loupfituserservice.userservice.business.record.login.out;

public record LoginResponse(
        String token,
        String type
) { }
