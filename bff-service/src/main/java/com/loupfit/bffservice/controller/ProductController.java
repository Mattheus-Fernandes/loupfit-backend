package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.ProductService;
import com.loupfit.bffservice.business.record.product.in.ProductRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdatePriceRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdateRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfit.bffservice.business.record.product.out.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") ProductRequest request,
            @RequestPart("file") MultipartFile file
    ) {

        return ResponseEntity.ok(productService.addProduct(token, request, file));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findProducts(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String createdBy

    ) {
        return ResponseEntity.ok(productService.filterProduct(token, name, category, size, createdBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById( @RequestHeader("Authorization") String token, @PathVariable Long id) {
        return ResponseEntity.ok(productService.filterProductById(token, id));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponse>> findProductsLowStock( @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(productService.filterProductLowStock(token));
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<List<ProductResponse>> findProductsBestSellers( @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(productService.filterProductBestSellers(token));
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
            @RequestBody ProductUpdatePriceRequest request
    ) {
        return ResponseEntity.ok(productService.updatePriceProduct(token, id, request));
    }

    @PatchMapping(value = "/{id}/image", consumes = "multipart/form-data")
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
