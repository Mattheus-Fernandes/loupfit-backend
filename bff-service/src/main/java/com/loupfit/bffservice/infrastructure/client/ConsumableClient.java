package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.consumable.in.ConsumableQuantityRequest;
import com.loupfit.bffservice.business.record.consumable.in.ConsumableRequest;
import com.loupfit.bffservice.business.record.consumable.out.ConsumableResponse;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "consumable-service", url = "${consumable.url}", configuration = FeignConfig.class)
public interface ConsumableClient {

    @PostMapping("/consumables")
    ConsumableResponse saveConsumables(@RequestHeader("Authorization") String token, @RequestBody ConsumableRequest request);

    @GetMapping("/consumables")
    List<ConsumableResponse> findAllConsumables(@RequestHeader("Authorization") String token);

    @DeleteMapping("/consumables/{id}")
    ConsumableResponse deleteConsumables(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/consumables/{id}")
    ConsumableResponse updateConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableRequest request,
            @PathVariable String id
    );

    @PatchMapping("/consumables/{id}/quantity")
    ConsumableResponse updateConsumablesQuantity(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableQuantityRequest request,
            @PathVariable String id
    );
}
