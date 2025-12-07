package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.in.ProductUpdateJsonDTO;
import com.loupfit.bffservice.business.dto.in.ProductUpdatePriceDTO;
import com.loupfit.bffservice.business.dto.in.ProductUpdateStockSalesDTO;
import com.loupfit.bffservice.business.dto.out.ProductDTO;
import com.loupfit.bffservice.infrastructure.client.config.ProductClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "product-service", url = "${product.url}", configuration = ProductClientConfig.class)
public interface ProductClient {

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ProductDTO saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") String productJson,
            @RequestPart("file") MultipartFile file
    );

    @GetMapping("/products")
    List<ProductDTO> findProducts(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String createdBy
    );

    @GetMapping("/products/{id}")
    ProductDTO findProductById(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @GetMapping("/products/low-stock")
    List<ProductDTO> findProductsLowStock(@RequestHeader("Authorization") String token);

    @GetMapping("/products/best-sellers")
    List<ProductDTO> findProductsBestSellers(@RequestHeader("Authorization") String token);

    @PatchMapping("/products/{id}")
    ProductDTO editProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateJsonDTO dto
    );

    @PatchMapping("/products/{id}/inventory")
    ProductDTO editProductStockSale(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesDTO dto
    );


    @PatchMapping("/products/{id}/price")
    ProductDTO editProductPrice(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdatePriceDTO price
    );


    @PatchMapping(value = "/products/{id}/image", consumes = "multipart/form-data")
    ProductDTO editProductImage(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    );

    @DeleteMapping("/products/{id}")
    ProductDTO deleteProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    );
}
