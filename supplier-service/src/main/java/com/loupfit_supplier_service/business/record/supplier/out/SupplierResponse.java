package com.loupfit_supplier_service.business.record.supplier.out;

public record SupplierResponse(
        String id,
        String name,
        String email,
        String phone,
        boolean active
) {
}
