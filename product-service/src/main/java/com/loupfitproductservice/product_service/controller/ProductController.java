package com.loupfitproductservice.product_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loupfitproductservice.product_service.business.ProductService;
import com.loupfitproductservice.product_service.business.record.product.in.ProductPriceUpdateRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductUpdateRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfitproductservice.product_service.business.record.product.out.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProductResponse> saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") String productJson,
            @RequestPart("file") MultipartFile file
    ) {
        try {

            ProductRequest request = new ObjectMapper().readValue(productJson, ProductRequest.class);

            return ResponseEntity.ok(productService.addProduct(token, request, file));

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String createdBy

    ) {

        if (name != null || category != null || size != null || createdBy != null) {
            return ResponseEntity.ok(productService.filterProduct(name, category, size, createdBy));
        }

        return ResponseEntity.ok(productService.filterAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.filterProductById(id));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponse>> findProductsLowStock() {
        return ResponseEntity.ok(productService.filterProductLowStock());
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<List<ProductResponse>> findProductsBestSellers() {
        return ResponseEntity.ok(productService.filterProductBestSellers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request
    ) {
        return ResponseEntity.ok(productService.updateProduct(token, id, request));
    }

    @PatchMapping("/{id}/inventory")
    public ResponseEntity<ProductResponse> editProductStockSale(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesRequest request
    ) {
        return ResponseEntity.ok(productService.updateStockAndSalesProduct(token, id, request));
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<ProductResponse> editProductPrice(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductPriceUpdateRequest request
    ) {
        return ResponseEntity.ok(productService.updatePriceProduct(token, id, request));
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<ProductResponse> editProductImage(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(productService.updateImageProduct(token, id, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productService.removeProduct(token, id));
    }

}
