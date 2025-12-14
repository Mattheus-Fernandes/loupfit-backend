package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.in.SupplierActiveDTO;
import com.loupfit.bffservice.business.dto.out.SupplierDTO;
import com.loupfit.bffservice.infrastructure.client.config.SupplierClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "supplier-service", url = "${supplier.url}", configuration = SupplierClientConfig.class)
public interface SupplierClient {

    @PostMapping("/suppliers")
    SupplierDTO saveSupplier(@RequestHeader("Authorization") String token, @RequestBody SupplierDTO dto);

    @GetMapping("/suppliers")
    List<SupplierDTO> findSupplier(@RequestHeader("Authorization") String token, @RequestParam(required = false) String name);

    @DeleteMapping("/suppliers/{id}")
    SupplierDTO deleteSupplier(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/suppliers/{id}")
    SupplierDTO editSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierDTO dto
    );

    @PatchMapping("/suppliers/{id}/active")
    SupplierDTO editActiveSupplier(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody SupplierActiveDTO dto
    );

}
