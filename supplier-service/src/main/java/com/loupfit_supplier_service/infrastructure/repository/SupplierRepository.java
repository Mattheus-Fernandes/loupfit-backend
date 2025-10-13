package com.loupfit_supplier_service.infrastructure.repository;

import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {

    boolean existsBySupplierName(String supplierName);
}
