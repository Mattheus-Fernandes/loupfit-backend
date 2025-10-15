package com.loupfitproductservice.product_service.infrastructure.repository;

import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);
}
