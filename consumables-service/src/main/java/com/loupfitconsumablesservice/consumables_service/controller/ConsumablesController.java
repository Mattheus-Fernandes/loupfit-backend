package com.loupfitconsumablesservice.consumables_service.controller;

import com.loupfitconsumablesservice.consumables_service.business.ConsumablesService;
import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesDTO;
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
    public ResponseEntity<ConsumablesDTO> saveConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesDTO consumablesDTO
    ) {
        return ResponseEntity.ok(consumablesService.addConsumable(token, consumablesDTO));
    }

    @GetMapping
    public ResponseEntity<List<ConsumablesDTO>> findAllConsumables() {
        return ResponseEntity.ok(consumablesService.filterAllConsumables());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumablesDTO> updateConsumables(
            @RequestHeader("Authorization") String token,
            @RequestBody ConsumablesDTO consumablesDTO,
            @PathVariable String id
    ) {
         return ResponseEntity.ok(consumablesService.editConsumable(token, id, consumablesDTO));
    }
}
