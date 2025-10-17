package com.loupfit_supplier_service.infrastructure.repository;

import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {

    boolean existsBySupplierName(String supplierName);

    List<Supplier> findBySupplierNameContainsIgnoreCase(String supplierName);
}
