package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.SupplierService;
import com.loupfit.bffservice.business.record.supplier.in.SupplierActiveRequest;
import com.loupfit.bffservice.business.record.supplier.in.SupplierRequest;
import com.loupfit.bffservice.business.record.supplier.out.SupplierResponse;
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
    public ResponseEntity<SupplierResponse> saveSupplier(
            @RequestHeader("Authorization") String token,
            @RequestBody SupplierRequest request
    ) {
        return ResponseEntity.ok(supplierService.addSupplier(token, request));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> findSupplier(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(supplierService.findSupplier(token, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponse> deleteSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(supplierService.removeSupplier(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> editSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierRequest request
    ) {
        return ResponseEntity.ok(supplierService.updateSupplier(token, id, request));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<SupplierResponse> editActiveSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierActiveRequest request
    ) {
        return ResponseEntity.ok(supplierService.updateActiveSupplier(token, id, request));
    }
}
