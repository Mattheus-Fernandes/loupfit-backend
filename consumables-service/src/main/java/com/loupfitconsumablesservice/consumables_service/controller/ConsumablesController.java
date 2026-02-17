package com.loupfitconsumablesservice.consumables_service.controller;

import com.loupfitconsumablesservice.consumables_service.business.ConsumablesService;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.in.ConsumableQuantityRequest;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.in.ConsumableRequest;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.out.ConsumableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumables")
@RequiredArgsConstructor
public class ConsumablesController {

    private final ConsumablesService consumablesService;

    @PostMapping
    public ResponseEntity<ConsumableResponse> saveConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableRequest request
    ) {
        return ResponseEntity.ok(consumablesService.addConsumable(token, request));
    }

    @GetMapping
    public ResponseEntity<List<ConsumableResponse>> findAllConsumables() {
        return ResponseEntity.ok(consumablesService.filterAllConsumables());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsumableResponse> deleteConsumables(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumablesService.removeConsumable(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumableResponse> updateConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableRequest request,
            @PathVariable String id
    ) {
         return ResponseEntity.ok(consumablesService.editConsumable(token, id, request));
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<ConsumableResponse> updateConsumablesQuantity(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableQuantityRequest request,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumablesService.editQuantityConsumable(token, id, request));
    }
}
