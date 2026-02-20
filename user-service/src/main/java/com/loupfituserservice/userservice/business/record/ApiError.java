package com.loupfituserservice.userservice.business.record;

public record ApiError(
        String msg,
        int status
) { }
