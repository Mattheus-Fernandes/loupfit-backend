package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.supplier.in.SupplierActiveRequest;
import com.loupfit.bffservice.business.record.supplier.in.SupplierRequest;
import com.loupfit.bffservice.business.record.supplier.out.SupplierResponse;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "supplier-service", url = "${supplier.url}", configuration = FeignConfig.class)
public interface SupplierClient {

    @PostMapping("/suppliers")
    SupplierResponse saveSupplier(@RequestHeader("Authorization") String token, @RequestBody SupplierRequest request);

    @GetMapping("/suppliers")
    List<SupplierResponse> findSupplier(@RequestHeader("Authorization") String token, @RequestParam(required = false) String name);

    @DeleteMapping("/suppliers/{id}")
    SupplierResponse deleteSupplier(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/suppliers/{id}")
    SupplierResponse editSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierRequest request
    );

    @PatchMapping("/suppliers/{id}/active")
    SupplierResponse editActiveSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierActiveRequest request
    );
}
