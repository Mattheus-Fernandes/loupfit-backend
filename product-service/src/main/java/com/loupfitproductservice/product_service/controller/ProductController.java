package com.loupfitproductservice.product_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loupfitproductservice.product_service.business.ProductService;
import com.loupfitproductservice.product_service.business.dto.product.ProductDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdateJsonDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdatePriceDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdateStockSalesDTO;
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
    public ResponseEntity<ProductDTO> saveProduct(
            @RequestHeader("Authorization") String token,
            @RequestPart("product") String productJson,
            @RequestPart("file") MultipartFile file
    ) {
        try {

            ProductDTO dto = new ObjectMapper().readValue(productJson, ProductDTO.class);

            return ResponseEntity.ok(productService.addProduct(token, dto, file));

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON", e);
        }
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.filterProductById(id));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDTO>> findProductsLowStock() {
        return ResponseEntity.ok(productService.filterProductLowStock());
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<List<ProductDTO>> findProductsBestSellers() {
        return ResponseEntity.ok(productService.filterProductBestSellers());
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

    @PatchMapping("/{id}/image")
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
