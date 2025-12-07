package com.loupfit.bffservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loupfit.bffservice.business.ProductService;
import com.loupfit.bffservice.business.dto.in.ProductUpdateJsonDTO;
import com.loupfit.bffservice.business.dto.in.ProductUpdatePriceDTO;
import com.loupfit.bffservice.business.dto.in.ProductUpdateStockSalesDTO;
import com.loupfit.bffservice.business.dto.out.ProductDTO;
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
    public ResponseEntity<ProductDTO> saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") ProductDTO dto,
            @RequestPart("file") MultipartFile file
    ) {

        return ResponseEntity.ok(productService.addProduct(token, dto, file));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findProducts(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String createdBy

    ) {
        return ResponseEntity.ok(productService.filterProduct(token, name, category, size, createdBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById( @RequestHeader("Authorization") String token, @PathVariable Long id) {
        return ResponseEntity.ok(productService.filterProductById(token, id));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDTO>> findProductsLowStock( @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(productService.filterProductLowStock(token));
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<List<ProductDTO>> findProductsBestSellers( @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(productService.filterProductBestSellers(token));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> editProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateJsonDTO dto
    ) {
        return ResponseEntity.ok(productService.updateProduct(token, id, dto));
    }

    @PatchMapping("/{id}/inventory")
    public ResponseEntity<ProductDTO> editProductStockSale(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdateStockSalesDTO dto
    ) {
        return ResponseEntity.ok(productService.updateStockAndSalesProduct(token, id, dto));
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<ProductDTO> editProductPrice(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ProductUpdatePriceDTO price
    ) {
        return ResponseEntity.ok(productService.updatePriceProduct(token, id, price));
    }

    @PatchMapping(value = "/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<ProductDTO> editProductImage(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(productService.updateImageProduct(token, id, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productService.removeProduct(token, id));
    }
}
