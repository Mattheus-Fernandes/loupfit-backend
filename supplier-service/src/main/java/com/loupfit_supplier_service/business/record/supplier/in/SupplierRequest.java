package com.loupfit_supplier_service.business.record.supplier.in;

public record SupplierRequest(
        String name,
        String email,
        String phone,
        boolean active
) {
}
