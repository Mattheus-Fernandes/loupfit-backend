package com.loupfit_supplier_service.business;

import com.loupfit_supplier_service.business.mapper.SupplierConverter;
import com.loupfit_supplier_service.business.mapper.SupplierUpdateConverter;
import com.loupfit_supplier_service.business.record.supplier.in.SupplierActiveRequest;
import com.loupfit_supplier_service.business.record.supplier.in.SupplierRequest;
import com.loupfit_supplier_service.business.record.supplier.out.SupplierResponse;
import com.loupfit_supplier_service.business.record.user.out.UserAuthenticatedResponse;
import com.loupfit_supplier_service.business.record.user.out.UserResponse;
import com.loupfit_supplier_service.infrastructure.client.UserClient;
import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import com.loupfit_supplier_service.infrastructure.enums.UserRole;
import com.loupfit_supplier_service.infrastructure.exceptions.ConflictException;
import com.loupfit_supplier_service.infrastructure.exceptions.ForbiddenException;
import com.loupfit_supplier_service.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfit_supplier_service.infrastructure.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierConverter supplierConverter;
    private final SupplierUpdateConverter supplierUpdateConverter;
    private final UserClient userClient;


    private UserAuthenticatedResponse userAuthenticated(String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserResponse user = userClient.getUserByUsername(token, username);

        if (user != null && user.username() != null) {
            return new UserAuthenticatedResponse(user.username(), user.role());
        }

        throw new ResourceNotFoundException("Usuário(a) não encontrado(a) " + username);
    }

    public SupplierResponse addSupplier(SupplierRequest request) {

        existBySupplierName(request.name());

        SupplierRequest data = new SupplierRequest(
                request.name(),
                request.email(),
                request.phone(),
                request.active()
        );

        Supplier supplier = supplierConverter.toEntity(data);

        return supplierConverter.toResponse(supplierRepository.save(supplier));
    }

    public void existBySupplierName(String username) {

        try {

            boolean exist = supplierRepository.existsByName(username);

            if (exist) {
                throw new ConflictException("Fornecedor já cadastrado " + username);
            }

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public List<SupplierResponse> filterAllSupplies() {

        try {

            return supplierConverter.toResponseList(
                    supplierRepository.findAll()
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public List<SupplierResponse> filterBySupplierName(String name) {

        try {

            return supplierConverter.toResponseList(
                    supplierRepository.findByNameContainsIgnoreCase(name)
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public SupplierResponse removeSupplier(String token, String id) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        hasPermission(user, "DELETE");

        Supplier entity = supplierRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Fornecedor não encontrado")
        );

        supplierRepository.deleteById(id);

        return supplierConverter.toResponse(entity);
    }

    public SupplierResponse updateSupplier(String token, String id, SupplierRequest request) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        hasPermission(user, "PUT");

        SupplierRequest data = new SupplierRequest(
                request.name(),
                request.email(),
                request.phone(),
                request.active()
        );

        Supplier entity = supplierRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Fornecedor não encontrado")
        );

        Supplier supplierEdit = supplierUpdateConverter.doUpdate(data, entity);

        return supplierConverter.toResponse(supplierRepository.save(supplierEdit));
    }

    public SupplierResponse updateActiveSupplier(String token, String id, SupplierActiveRequest request) {
        UserAuthenticatedResponse user = userAuthenticated(token);

        hasPermission(user, "PUT");

        Supplier entity = supplierRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Fornecedor não encontrado")
        );

        entity.setActive(request.active());

        return supplierConverter.toResponse(supplierRepository.save(entity));
    }

    private void hasPermission(UserAuthenticatedResponse user, String method) {
        boolean permitted = user.role() == UserRole.OWNER || user.role() == UserRole.ADMIN || user.role() == UserRole.EDITOR;

        String methodType = "DELETE".equals(method) ? "excluir" : "editar";

        if (!permitted) {
            throw new ForbiddenException("OPSS! Você não tem PERMISSÃO para " + methodType + " consumíveis.");
        }
    }

}
