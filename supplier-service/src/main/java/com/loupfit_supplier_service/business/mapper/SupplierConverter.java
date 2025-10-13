package com.loupfit_supplier_service.business.mapper;

import com.loupfit_supplier_service.business.dto.SupplierDTO;
import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierConverter {

    Supplier supplierEntity(SupplierDTO dto);

    SupplierDTO supplierDTO(Supplier supplier);

    List<SupplierDTO> supplierDTOList(List<Supplier> entities);
}
