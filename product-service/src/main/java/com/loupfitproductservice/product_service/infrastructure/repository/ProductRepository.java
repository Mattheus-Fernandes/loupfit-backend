package com.loupfitproductservice.product_service.infrastructure.repository;

import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByCategoryContainsIgnoreCase(String category);

    List<Product> findBySizeContainsIgnoreCase(String size);

    List<Product> findByCreatedByIgnoreCase(String createdBy);

    List<Product> findByStockLessThan(Integer stock);

    List<Product> findBySalesGreaterThan(Integer stock);

}
