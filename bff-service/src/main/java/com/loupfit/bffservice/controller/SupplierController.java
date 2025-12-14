package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.SupplierService;
import com.loupfit.bffservice.business.dto.in.SupplierActiveDTO;
import com.loupfit.bffservice.business.dto.out.SupplierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDTO> saveSupplier(
            @RequestHeader("Authorization") String token,
            @RequestBody SupplierDTO dto
    ) {
        return ResponseEntity.ok(supplierService.addSupplier(token, dto));
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findSupplier(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(supplierService.findSupplier(token, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> deleteSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(supplierService.removeSupplier(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> editSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierDTO dto
    ) {
        return ResponseEntity.ok(supplierService.updateSupplier(token, id, dto));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<SupplierDTO> editActiveSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierActiveDTO dto
    ) {
        return ResponseEntity.ok(supplierService.updateActiveSupplier(token, id, dto));
    }
}
