package com.loupfitassetservice.asset_service.business.record.asset.in;

import java.math.BigDecimal;

public record AssetRequest(
        String name,
        String description,
        Integer quantity,
        BigDecimal costValue,
        String placePurchase,
        String createdBy
) {
}
