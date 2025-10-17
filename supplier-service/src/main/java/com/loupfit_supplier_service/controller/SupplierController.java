package com.loupfit_supplier_service.controller;

import com.loupfit_supplier_service.business.SupplierService;
import com.loupfit_supplier_service.business.dto.SupplierActiveDTO;
import com.loupfit_supplier_service.business.dto.SupplierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDTO> saveSupplier(@RequestBody SupplierDTO dto) {
        return ResponseEntity.ok(supplierService.addSupplier(dto));
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findSupplier(
            @RequestParam(required = false) String name
    ) {

        if (name == null) {
            return ResponseEntity.ok(supplierService.filterAllSupplies());
        }

        return ResponseEntity.ok(supplierService.filterBySupplierName(name));

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
