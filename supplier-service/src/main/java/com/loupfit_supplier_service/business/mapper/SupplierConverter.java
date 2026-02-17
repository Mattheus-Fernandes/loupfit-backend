package com.loupfit_supplier_service.business.mapper;

import com.loupfit_supplier_service.business.record.supplier.in.SupplierRequest;
import com.loupfit_supplier_service.business.record.supplier.out.SupplierResponse;
import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierConverter {

    Supplier toEntity(SupplierRequest request);

    SupplierResponse toResponse(Supplier entity);

    List<SupplierResponse> toResponseList(List<Supplier> entities);
}
