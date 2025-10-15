package com.loupfitproductservice.product_service.controller;

import com.loupfitproductservice.product_service.business.ProductService;
import com.loupfitproductservice.product_service.business.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
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
}
