package com.loupfitorderservice.order_service.infrastructure.security.product;

import com.loupfitorderservice.order_service.business.dto.product.ProductDTO;
import com.loupfitorderservice.order_service.business.dto.product.ProductUpdateStockSalesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "${product.url}")
public interface ProductClient {

    @PatchMapping("/products/{id}/inventory")
    ProductDTO updateInventory(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesDTO dto
    );
}
