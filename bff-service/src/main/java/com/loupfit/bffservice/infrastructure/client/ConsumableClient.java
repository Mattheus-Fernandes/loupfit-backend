package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.in.ConsumablesQuantityDTO;
import com.loupfit.bffservice.business.dto.out.ConsumablesDTO;
import com.loupfit.bffservice.infrastructure.client.config.ConsumableClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "consumable-service", url = "${consumable.url}", configuration = ConsumableClientConfig.class)
public interface ConsumableClient {

    @PostMapping("/consumables")
    ConsumablesDTO saveConsumables(@RequestHeader("Authorization") String token, @RequestBody ConsumablesDTO consumablesDTO);

    @GetMapping("/consumables")
    List<ConsumablesDTO> findAllConsumables(@RequestHeader("Authorization") String token);

    @DeleteMapping("/consumables/{id}")
    ConsumablesDTO deleteConsumables(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/consumables/{id}")
    ConsumablesDTO updateConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesDTO consumablesDTO,
            @PathVariable String id
    );

    @PatchMapping("/consumables/{id}/quantity")
    ConsumablesDTO updateConsumablesQuantity(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesQuantityDTO consumablesQuantityDTO,
            @PathVariable String id
    );
}
