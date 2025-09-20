package com.loupfitconsumablesservice.consumables_service.controller;

import com.loupfitconsumablesservice.consumables_service.business.ConsumablesService;
import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumables")
@RequiredArgsConstructor
public class ConsumablesController {

    private final ConsumablesService consumablesService;

    @PostMapping
    public ResponseEntity<ConsumablesDTO> saveConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesDTO consumablesDTO
    ) {
        return ResponseEntity.ok(consumablesService.addConsumable(token, consumablesDTO));
    }
}
