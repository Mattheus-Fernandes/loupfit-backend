package com.loupfitproductservice.product_service.business;

import com.loupfitproductservice.product_service.business.converter.ProductConverter;
import com.loupfitproductservice.product_service.business.dto.ProductDTO;
import com.loupfitproductservice.product_service.business.dto.UserDTO;
import com.loupfitproductservice.product_service.infrastructure.client.UserClient;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import com.loupfitproductservice.product_service.infrastructure.exceptions.ConflictExcpetion;
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
}
