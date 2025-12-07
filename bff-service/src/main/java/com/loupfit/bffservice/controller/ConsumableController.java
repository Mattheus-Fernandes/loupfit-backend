package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.ConsumableService;
import com.loupfit.bffservice.business.dto.in.ConsumablesQuantityDTO;
import com.loupfit.bffservice.business.dto.out.ConsumablesDTO;
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
    public ResponseEntity<ConsumablesDTO> saveConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesDTO consumablesDTO
    ) {
        return ResponseEntity.ok(consumableService.addConsumable(token, consumablesDTO));
    }

    @GetMapping
    public ResponseEntity<List<ConsumablesDTO>> findAllConsumables(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(consumableService.filterAllConsumables(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsumablesDTO> deleteConsumables(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumableService.removeConsumable(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumablesDTO> updateConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesDTO consumablesDTO,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumableService.editConsumable(token, id, consumablesDTO));
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<ConsumablesDTO> updateConsumablesQuantity(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesQuantityDTO consumablesQuantityDTO,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(consumableService.editQuantityConsumable(token, id, consumablesQuantityDTO));
    }
}
