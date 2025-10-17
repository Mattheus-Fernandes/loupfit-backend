package com.loupfitproductservice.product_service.business;

import com.loupfitproductservice.product_service.business.converter.ProductConverter;
import com.loupfitproductservice.product_service.business.converter.ProductUpdateConverter;
import com.loupfitproductservice.product_service.business.dto.product.ProductDTO;
import com.loupfitproductservice.product_service.business.dto.UserDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdateJsonDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdatePriceDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdateStockSalesDTO;
import com.loupfitproductservice.product_service.infrastructure.client.UserClient;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import com.loupfitproductservice.product_service.infrastructure.enums.UserRole;
import com.loupfitproductservice.product_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitproductservice.product_service.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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

    private UserDTO authenticatedUser(String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        UserDTO userDTO = userClient.getUserByUsername(token, username);

        if (userDTO != null && userDTO.getUsername() != null) {
            return new UserDTO(userDTO.getUsername(), userDTO.getRole());
        }

        throw new ConflictExcpetion("Usuário(a) não encontrado(a) " + username);
    }

    public ProductDTO addProduct(String token, ProductDTO dto, MultipartFile file) {

        existProduct(dto.getName());

        UserDTO user = authenticatedUser(token);
        dto.setCreatedBy(user.getUsername());

        String imageUrl = minioService.uploadFile(file);
        dto.setImageUrl(imageUrl);

        Product newProduct = productConverter.productEntity(dto);

        return productConverter.productDTO(productRepository.save(newProduct));
    }

    public void existProduct(String name) {
        try {
            boolean exist = productRepository.existsByName(name);

            if (exist) {
                throw new ConflictExcpetion("Produto já cadastrado " + name);

            }

        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public List<ProductDTO> filterAllProduct() {
        try {

            return productConverter.productsDTOList(
                    productRepository.findAll()
            );
        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public List<ProductDTO> filterProduct(String name, String category, String size, String createdBy) {

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
                throw new ConflictExcpetion("Nenhum produto encontrado");
            }

            return productConverter.productsDTOList(products);

        } catch (ConfigDataException e) {
            throw new ConflictExcpetion(e.getMessage());

        }
    }

    public List<ProductDTO> filterProductLowStock() {

        try {
            List<Product> products = productRepository.findByStockLessThan(4);

            if (products.isEmpty()) {
                throw new ConflictExcpetion("Nenhum produto com baixo estoque encontrado.");
            }

            return productConverter.productsDTOList(products);
        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public List<ProductDTO> filterProductBestSellers() {

        try {
            List<Product> products = productRepository.findBySalesGreaterThan(10);

            if (products.isEmpty()) {
                throw new ConflictExcpetion("Nenhum produto encontrado.");
            }

            return productConverter.productsDTOList(products);
        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public ProductDTO updateProduct(String token, Long id, ProductUpdateJsonDTO dto) {

        UserDTO user = authenticatedUser(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.EDITOR;

        if (!permitted) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar produto.");
        }

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Produto não encontrado")
        );

        productUpdateConverter.productUpdateJson(dto, entity);

        return productConverter.productDTO(productRepository.save(entity));
    }

    public ProductDTO updateStockAndSalesProduct(String token, Long id, ProductUpdateStockSalesDTO dto) {

        UserDTO user = authenticatedUser(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.EDITOR;

        if (!permitted) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar produto.");
        }

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Produto não encontrado")
        );

        String operation = dto.getOperation().toUpperCase();

        if ("STOCK".equalsIgnoreCase(dto.getInventory())) {

            switch (operation) {

                case "DECREASE":

                    if (entity.getStock() < dto.getQuantity()) {
                        throw new ConflictExcpetion("Estoque insuficiente para realizar a operação");
                    }


                    entity.setStock(entity.getStock() - dto.getQuantity());

                    break;

                case "INCREASE":

                    entity.setStock(entity.getStock() + dto.getQuantity());

                    break;

                default:
                    break;
            }
        }

        if ("SALES".equalsIgnoreCase(dto.getInventory())) {

            switch (operation) {

                case "DECREASE":

                    if (entity.getSales() < dto.getQuantity()) {
                        throw new ConflictExcpetion("Não é possível remover mais vendas do que registradas");
                    }

                    entity.setSales(entity.getSales() - dto.getQuantity());

                    break;

                case "INCREASE":

                    entity.setSales(entity.getSales() + dto.getQuantity());

                    break;

                default:
                    break;
            }
        }

        return productConverter.productDTO(productRepository.save(entity));
    }

    public ProductDTO updatePriceProduct(String token, Long id, ProductUpdatePriceDTO price) {

        UserDTO user = authenticatedUser(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.EDITOR;

        if (!permitted) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar produto.");
        }

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Produto não encontrado")
        );

        productUpdateConverter.productUpdatePrice(price, entity);

        return productConverter.productDTO(productRepository.save(entity));

    }

    public ProductDTO updateImageProduct(String token, Long id, MultipartFile file) {

        UserDTO user = authenticatedUser(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.EDITOR;

        if (!permitted) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar produto.");
        }

        Product entity = productRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Produto não encontrado")
        );

        // Remove Image from MinIO
        if (entity.getImageUrl() != null && !entity.getImageUrl().isEmpty()) {
            String fileName = extractFileName(entity.getImageUrl());
            minioService.removeFile(fileName);
        }

        // Update New Image
        String newImage = minioService.uploadFile(file);
        entity.setImageUrl(newImage);

        return productConverter.productDTO(productRepository.save(entity));

    }

    private String extractFileName(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }
}
