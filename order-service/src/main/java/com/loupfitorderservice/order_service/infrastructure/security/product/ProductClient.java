package com.loupfitorderservice.order_service.infrastructure.security.product;

import com.loupfitorderservice.order_service.business.dto.product.ProductDTO;
import com.loupfitorderservice.order_service.business.dto.product.ProductUpdateStockSalesDTO;
import com.loupfitorderservice.order_service.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "${product.url}", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("products/{id}")
    ProductDTO getProductById(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @PatchMapping("/products/{id}/inventory")
    ProductDTO updateInventory(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesDTO dto
    );
}
