package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.supplier.in.SupplierActiveRequest;
import com.loupfit.bffservice.business.record.supplier.in.SupplierRequest;
import com.loupfit.bffservice.business.record.supplier.out.SupplierResponse;
import com.loupfit.bffservice.infrastructure.client.SupplierClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierClient supplierClient;

    public SupplierResponse addSupplier(String token, SupplierRequest request) {
        return supplierClient.saveSupplier(token, request);
    }

    public List<SupplierResponse> findSupplier(String token, String name) {
        return supplierClient.findSupplier(token, name);
    }

    public SupplierResponse removeSupplier(String token, String id) {
        return supplierClient.deleteSupplier(token, id);
    }

    public SupplierResponse updateSupplier(String token, String id, SupplierRequest request) {
        return supplierClient.editSupplier(token, id, request);
    }

    public SupplierResponse updateActiveSupplier(String token, String id, SupplierActiveRequest request) {
       return supplierClient.editActiveSupplier(token, id, request);
    }
}
