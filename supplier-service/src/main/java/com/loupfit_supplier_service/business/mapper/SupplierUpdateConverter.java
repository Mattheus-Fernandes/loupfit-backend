package com.loupfit_supplier_service.business.mapper;

import com.loupfit_supplier_service.business.record.supplier.in.SupplierRequest;
import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierUpdateConverter {

    Supplier doUpdate(SupplierRequest request, @MappingTarget Supplier entity);
}
