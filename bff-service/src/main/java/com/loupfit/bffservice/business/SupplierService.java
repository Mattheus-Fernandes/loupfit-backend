package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.in.SupplierActiveDTO;
import com.loupfit.bffservice.business.dto.out.SupplierDTO;
import com.loupfit.bffservice.infrastructure.client.SupplierClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierClient supplierClient;

    public SupplierDTO addSupplier(String token, SupplierDTO dto) {
        return supplierClient.saveSupplier(token, dto);
    }

    public List<SupplierDTO> findSupplier(String token, String name) {
        return supplierClient.findSupplier(token, name);
    }

    public SupplierDTO removeSupplier(String token, String id) {
        return supplierClient.deleteSupplier(token, id);
    }

    public SupplierDTO updateSupplier(String token, String id, SupplierDTO dto) {
        return supplierClient.editSupplier(token, id, dto);
    }

    public SupplierDTO updateActiveSupplier(String token, String id, SupplierActiveDTO dto) {
       return supplierClient.editActiveSupplier(token, id, dto);
    }
}
