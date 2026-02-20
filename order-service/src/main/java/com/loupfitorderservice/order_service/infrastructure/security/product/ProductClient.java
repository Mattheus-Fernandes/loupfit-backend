package com.loupfitorderservice.order_service.infrastructure.security.product;

import com.loupfitorderservice.order_service.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfitorderservice.order_service.business.record.product.out.ProductResponse;
import com.loupfitorderservice.order_service.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "${product.url}", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("products/{id}")
    ProductResponse getProductById(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @PatchMapping("/products/{id}/inventory")
    ProductResponse updateInventory(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesRequest request
    );
}
