package com.loupfit.bffservice.business.record.supplier.out;

public record SupplierResponse(
        String id,
        String name,
        String email,
        String phone,
        boolean active
) {
}
