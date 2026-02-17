package com.loupfitassetservice.asset_service.business.record.asset.out;

import java.math.BigDecimal;

public record AssetResponse(
        String id,
        String name,
        String description,
        Integer quantity,
        BigDecimal costValue,
        String placePurchase,
        String createdBy
) {
}
