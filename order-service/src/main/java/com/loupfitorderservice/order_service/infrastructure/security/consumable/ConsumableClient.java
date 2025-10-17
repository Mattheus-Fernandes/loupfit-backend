package com.loupfitorderservice.order_service.infrastructure.security.consumable;

import com.loupfitorderservice.order_service.business.dto.consumable.ConsumableDTO;
import com.loupfitorderservice.order_service.business.dto.consumable.ConsumablesQuantityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "consumable-service", url = "${consumable.url}")
public interface ConsumableClient {

    @PatchMapping("consumables/{id}/quantity")
    ConsumableDTO updateQuantity(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody ConsumablesQuantityDTO dto
    );
}
