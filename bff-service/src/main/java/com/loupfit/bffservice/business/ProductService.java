package com.loupfit.bffservice.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loupfit.bffservice.business.dto.in.ProductUpdateJsonDTO;
import com.loupfit.bffservice.business.dto.in.ProductUpdatePriceDTO;
import com.loupfit.bffservice.business.dto.in.ProductUpdateStockSalesDTO;
import com.loupfit.bffservice.business.dto.out.ProductDTO;
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

    public ProductDTO addProduct(String token, ProductDTO dto, MultipartFile file) {

        try {
            // DTO to JSON
            String productJson = objectMapper.writeValueAsString(dto);

            return productClient.saveProduct(token, productJson, file);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar multipart", e);
        }
    }

    public ProductDTO filterProductById(String token, Long id) {
        return productClient.findProductById(token, id);
    }

    public List<ProductDTO> filterProduct(String token, String name, String category, String size, String createdBy) {
        return productClient.findProducts(token, name, category, size, createdBy);
    }

    public List<ProductDTO> filterProductLowStock(String token) {
        return productClient.findProductsLowStock(token);
    }

    public List<ProductDTO> filterProductBestSellers(String token) {
        return productClient.findProductsBestSellers(token);
    }

    public ProductDTO updateProduct(String token, Long id, ProductUpdateJsonDTO dto) {
        return productClient.editProduct(token, id, dto);
    }

    public ProductDTO updateStockAndSalesProduct(String token, Long id, ProductUpdateStockSalesDTO dto) {
        return productClient.editProductStockSale(token, id, dto);
    }

    public ProductDTO updatePriceProduct(String token, Long id, ProductUpdatePriceDTO price) {
        return productClient.editProductPrice(token, id, price);
    }

    public ProductDTO updateImageProduct(String token, Long id, MultipartFile file) {
        return productClient.editProductImage(token, id, file);
    }

    public ProductDTO removeProduct(String token, Long id) {
        return productClient.deleteProduct(token, id);
    }
}
