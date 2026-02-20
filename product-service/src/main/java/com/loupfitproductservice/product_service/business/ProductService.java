package com.loupfitproductservice.product_service.business;

import com.loupfitproductservice.product_service.business.mapper.ProductConverter;
import com.loupfitproductservice.product_service.business.mapper.ProductUpdateConverter;
import com.loupfitproductservice.product_service.business.record.product.in.ProductPriceUpdateRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductUpdateRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfitproductservice.product_service.business.record.product.out.ProductResponse;
import com.loupfitproductservice.product_service.business.record.user.out.UserResponse;
import com.loupfitproductservice.product_service.infrastructure.client.UserClient;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import com.loupfitproductservice.product_service.infrastructure.enums.UserRole;
import com.loupfitproductservice.product_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitproductservice.product_service.infrastructure.exceptions.ForbiddenException;
import com.loupfitproductservice.product_service.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfitproductservice.product_service.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ProductUpdateConverter productUpdateConverter;
    private final MinioService minioService;
    private final UserClient userClient;

    private UserResponse authenticatedUser(String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        UserResponse user = userClient.getUserByUsername(token, username);

        if (user != null && user.username() != null) {
            return new UserResponse(user.username(), user.role());
        }

        throw new ResourceNotFoundException("Usuário(a) não encontrado(a) " + username);
    }

    public ProductResponse addProduct(String token, ProductRequest request, MultipartFile file) {

        UserResponse user = authenticatedUser(token);

        String imageUrl = minioService.uploadFile(file);

        ProductRequest data = new ProductRequest(
                request.name(),
                request.description(),
                imageUrl,
                request.price(),
                request.costPrice(),
                request.stock(),
                request.category(),
                request.subcategory(),
                request.size(),
                request.material(),
                request.sales(),
                user.username()
        );

        Product product = productConverter.toEntity(data);

        return productConverter.toResponse(productRepository.save(product));
    }


    public List<ProductResponse> filterAllProduct() {
        try {

            return productConverter.toResponseList(
                    productRepository.findAll()
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public ProductResponse filterProductById(Long id) {
        try {

            return productConverter.toResponse(
                    productRepository.findById(id).orElseThrow(
                            () -> new ResourceNotFoundException("Nenhum produto encontrado")
                    )
            );

        } catch (ConfigDataException e) {
            throw new ConflictExcpetion(e.getMessage());

        }
    }

    public List<ProductResponse> filterProduct(String name, String category, String size, String createdBy) {

        try {

            List<Product> products = new ArrayList<Product>();

            if (name != null && !name.isEmpty()) {
                products = productRepository.findByNameContainsIgnoreCase(name);
            } else if (category != null && !category.isEmpty()) {
                products = productRepository.findByCategoryContainsIgnoreCase(category);
            } else if (size != null && !size.isEmpty()) {
                products = productRepository.findBySizeContainsIgnoreCase(size);
            } else if (createdBy != null && !createdBy.isEmpty()) {
                products = productRepository.findByCreatedByIgnoreCase(createdBy);
            }

            if (products.isEmpty()) {
                throw new ResourceNotFoundException("Nenhum produto encontrado");
            }

            return productConverter.toResponseList(products);

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());

        }
    }

    public List<ProductResponse> filterProductLowStock() {

        try {
            List<Product> products = productRepository.findByStockLessThan(4);

            if (products.isEmpty()) {
                throw new ResourceNotFoundException("Nenhum produto com baixo estoque encontrado.");
            }

            return productConverter.toResponseList(products);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public List<ProductResponse> filterProductBestSellers() {

        try {
            List<Product> products = productRepository.findBySalesGreaterThan(10);

            if (products.isEmpty()) {
                throw new ResourceNotFoundException("Nenhum produto encontrado.");
            }

            return productConverter.toResponseList(products);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public ProductResponse updateProduct(String token, Long id, ProductUpdateRequest request) {

        UserResponse user = authenticatedUser(token);

        hasPermission(user, "PUT");

        ProductUpdateRequest data = new ProductUpdateRequest(
                request.id(),
                request.name(),
                request.description(),
                request.price(),
                request.costPrice(),
                request.stock(),
                request.category(),
                request.subcategory(),
                request.size(),
                request.color(),
                request.material(),
                request.sales(),
                user.username()
        );

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado")
        );

        Product productEdit = productUpdateConverter.doUpdate(data, entity);

        return productConverter.toResponse(productRepository.save(productEdit));
    }

    public ProductResponse updateStockAndSalesProduct(String token, Long id, ProductUpdateStockSalesRequest request) {

        UserResponse user = authenticatedUser(token);

        hasPermission(user, "PATCH");

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado")
        );

        String operation = request.operation().toUpperCase();

        if ("STOCK".equalsIgnoreCase(request.operation())) {

            switch (operation) {

                case "DECREASE":

                    if (entity.getStock() < request.quantity()) {
                        throw new ConflictExcpetion("Estoque insuficiente para realizar a operação");
                    }

                    entity.setStock(entity.getStock() - request.quantity());

                    break;

                case "INCREASE":

                    entity.setStock(entity.getStock() + request.quantity());

                    break;

                default:
                    break;
            }
        }

        if ("SALES".equalsIgnoreCase(request.inventory())) {

            switch (operation) {

                case "DECREASE":

                    if (entity.getSales() < request.quantity()) {
                        throw new ConflictExcpetion("Não é possível remover mais vendas do que registradas");
                    }

                    entity.setSales(entity.getSales() - request.quantity());

                    break;

                case "INCREASE":

                    entity.setSales(entity.getSales() + request.quantity());

                    break;

                default:
                    break;
            }
        }

        return productConverter.toResponse(productRepository.save(entity));
    }

    public ProductResponse updatePriceProduct(String token, Long id, ProductPriceUpdateRequest request) {

        UserResponse user = authenticatedUser(token);

        hasPermission(user, "PATCH");

        ProductPriceUpdateRequest data = new ProductPriceUpdateRequest(
                request.price()
        );

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado")
        );

        Product productEdit = productUpdateConverter.doUpdatePrice(data, entity);

        return productConverter.toResponse(productRepository.save(productEdit));

    }

    public ProductResponse updateImageProduct(String token, Long id, MultipartFile file) {

        UserResponse user = authenticatedUser(token);

        hasPermission(user, "PATCH");

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado")
        );

        // Remove Image from MinIO
        if (entity.getImageUrl() != null && !entity.getImageUrl().isEmpty()) {
            String fileName = extractFileName(entity.getImageUrl());
            minioService.removeFile(fileName);
        }

        // Update New Image
        String newImage = minioService.uploadFile(file);
        entity.setImageUrl(newImage);

        return productConverter.toResponse(productRepository.save(entity));

    }

    private String extractFileName(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }

    public ProductResponse removeProduct(String token, Long id) {
        UserResponse user = authenticatedUser(token);

       hasPermission(user, "DELETE");

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado")
        );

        // First remove from MIniO
        if (entity.getImageUrl() != null && !entity.getImageUrl().isEmpty()) {
            String fileName = extractFileName(entity.getImageUrl());
            minioService.removeFile(fileName);
        }

        // Second remove from DB
        productRepository.deleteById(id);

        return productConverter.toResponse(entity);
    }

    private void hasPermission(UserResponse user, String method) {
        boolean permitted = user.role() == UserRole.OWNER || user.role() == UserRole.ADMIN || user.role() == UserRole.EDITOR;

        String methodType = "DELETE".equals(method) ? "excluir" : "editar";

        if (!permitted) {
            throw new ForbiddenException("OPSS! Você não tem PERMISSÃO para " + methodType + " consumíveis.");
        }
    }
}
