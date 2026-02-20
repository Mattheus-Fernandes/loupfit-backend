package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.product.in.ProductUpdatePriceRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdateRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfit.bffservice.business.record.product.out.ProductResponse;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "product-service", url = "${product.url}", configuration = FeignConfig.class)
public interface ProductClient {

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ProductResponse saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") String productJson,
            @RequestPart("file") MultipartFile file
    );

    @GetMapping("/products")
    List<ProductResponse> findProducts(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String createdBy
    );

    @GetMapping("/products/{id}")
    ProductResponse findProductById(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @GetMapping("/products/low-stock")
    List<ProductResponse> findProductsLowStock(@RequestHeader("Authorization") String token);

    @GetMapping("/products/best-sellers")
    List<ProductResponse> findProductsBestSellers(@RequestHeader("Authorization") String token);

    @PatchMapping("/products/{id}")
    ProductResponse editProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request
    );

    @PatchMapping("/products/{id}/inventory")
    ProductResponse editProductStockSale(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesRequest request
    );


    @PatchMapping("/products/{id}/price")
    ProductResponse editProductPrice(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdatePriceRequest request
    );


    @PatchMapping(value = "/products/{id}/image", consumes = "multipart/form-data")
    ProductResponse editProductImage(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    );

    @DeleteMapping("/products/{id}")
    ProductResponse deleteProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    );
}
