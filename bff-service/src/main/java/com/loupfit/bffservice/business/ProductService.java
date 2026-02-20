package com.loupfit.bffservice.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loupfit.bffservice.business.record.product.in.ProductRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdatePriceRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdateRequest;
import com.loupfit.bffservice.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfit.bffservice.business.record.product.out.ProductResponse;
import com.loupfit.bffservice.infrastructure.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;
    private final ObjectMapper objectMapper;

    public ProductResponse addProduct(String token, ProductRequest request, MultipartFile file) {

        try {
            // DTO to JSON
            String productJson = objectMapper.writeValueAsString(request);

            return productClient.saveProduct(token, productJson, file);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar multipart", e);
        }
    }

    public ProductResponse filterProductById(String token, Long id) {
        return productClient.findProductById(token, id);
    }

    public List<ProductResponse> filterProduct(String token, String name, String category, String size, String createdBy) {
        return productClient.findProducts(token, name, category, size, createdBy);
    }

    public List<ProductResponse> filterProductLowStock(String token) {
        return productClient.findProductsLowStock(token);
    }

    public List<ProductResponse> filterProductBestSellers(String token) {
        return productClient.findProductsBestSellers(token);
    }

    public ProductResponse updateProduct(String token, Long id, ProductUpdateRequest request) {
        return productClient.editProduct(token, id, request);
    }

    public ProductResponse updateStockAndSalesProduct(String token, Long id, ProductUpdateStockSalesRequest request) {
        return productClient.editProductStockSale(token, id, request);
    }

    public ProductResponse updatePriceProduct(String token, Long id, ProductUpdatePriceRequest request) {
        return productClient.editProductPrice(token, id, request);
    }

    public ProductResponse updateImageProduct(String token, Long id, MultipartFile file) {
        return productClient.editProductImage(token, id, file);
    }

    public ProductResponse removeProduct(String token, Long id) {
        return productClient.deleteProduct(token, id);
    }
}
