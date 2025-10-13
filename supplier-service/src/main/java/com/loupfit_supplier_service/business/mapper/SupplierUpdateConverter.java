package com.loupfit_supplier_service.business.mapper;

import com.loupfit_supplier_service.business.dto.SupplierDTO;
import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierUpdateConverter {

    void supplierUpdate(SupplierDTO dto, @MappingTarget Supplier entity);
}
