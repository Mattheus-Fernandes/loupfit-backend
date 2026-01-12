package com.loupfituserservice.userservice.business.record;

public record LoginRequest(
        String username,
        String password
) { }
