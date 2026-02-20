package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.ConsumableService;
import com.loupfit.bffservice.business.record.consumable.in.ConsumableQuantityRequest;
import com.loupfit.bffservice.business.record.consumable.in.ConsumableRequest;
import com.loupfit.bffservice.business.record.consumable.out.ConsumableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumables")
@RequiredArgsConstructor
public class ConsumableController {

    private final ConsumableService consumableService;

    @PostMapping
    public ResponseEntity<ConsumableResponse> saveConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableRequest request
    ) {
        return ResponseEntity.ok(consumableService.addConsumable(token, request));
    }

    @GetMapping
    public ResponseEntity<List<ConsumableResponse>> findAllConsumables(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(consumableService.filterAllConsumables(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsumableResponse> deleteConsumables(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumableService.removeConsumable(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumableResponse> updateConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableRequest request,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumableService.editConsumable(token, id, request));
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<ConsumableResponse> updateConsumablesQuantity(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumableQuantityRequest request,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumableService.editQuantityConsumable(token, id, request));
    }
}
