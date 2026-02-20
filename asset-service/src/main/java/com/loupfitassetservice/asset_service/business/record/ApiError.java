package com.loupfitassetservice.asset_service.business.record;

public record ApiError(
        String msg,
        int status
) { }
