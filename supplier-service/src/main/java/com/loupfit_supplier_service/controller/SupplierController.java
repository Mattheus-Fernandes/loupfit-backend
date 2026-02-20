package com.loupfit_supplier_service.controller;

import com.loupfit_supplier_service.business.SupplierService;
import com.loupfit_supplier_service.business.record.supplier.in.SupplierActiveRequest;
import com.loupfit_supplier_service.business.record.supplier.in.SupplierRequest;
import com.loupfit_supplier_service.business.record.supplier.out.SupplierResponse;
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
    public ResponseEntity<SupplierResponse> saveSupplier(@RequestBody SupplierRequest request) {
        return ResponseEntity.ok(supplierService.addSupplier(request));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> findSupplier(
            @RequestParam(required = false) String name
    ) {

        if (name == null) {
            return ResponseEntity.ok(supplierService.filterAllSupplies());
        }

        return ResponseEntity.ok(supplierService.filterBySupplierName(name));

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
