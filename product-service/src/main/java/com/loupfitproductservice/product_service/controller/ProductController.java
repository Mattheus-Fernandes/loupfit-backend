package com.loupfitproductservice.product_service.controller;

import com.loupfitproductservice.product_service.business.ProductService;
import com.loupfitproductservice.product_service.business.dto.ProductDTO;
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

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") ProductDTO dto,
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(productService.addProduct(token, dto, file));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findProducts(
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

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDTO>> findProductsLowStock() {
        return ResponseEntity.ok(productService.filterProductLowStock());
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<List<ProductDTO>> findProductsBestSellers() {
        return ResponseEntity.ok(productService.filterProductBestSellers());
    }


}
