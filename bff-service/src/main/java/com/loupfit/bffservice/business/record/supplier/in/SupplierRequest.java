package com.loupfit.bffservice.business.record.supplier.in;

public record SupplierRequest(
        String name,
        String email,
        String phone,
        boolean active
) {
}
