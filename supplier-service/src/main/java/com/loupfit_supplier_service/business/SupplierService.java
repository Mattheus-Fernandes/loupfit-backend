package com.loupfit_supplier_service.business;

import com.loupfit_supplier_service.business.dto.AuthenticatedUser;
import com.loupfit_supplier_service.business.dto.SupplierDTO;
import com.loupfit_supplier_service.business.dto.UserDTO;
import com.loupfit_supplier_service.business.mapper.SupplierConverter;
import com.loupfit_supplier_service.business.mapper.SupplierUpdateConverter;
import com.loupfit_supplier_service.infrastructure.client.UserClient;
import com.loupfit_supplier_service.infrastructure.entity.Supplier;
import com.loupfit_supplier_service.infrastructure.enums.UserRole;
import com.loupfit_supplier_service.infrastructure.exceptions.ConflictException;
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


    private AuthenticatedUser userAuthenticated(String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserDTO userDTO = userClient.getUserByUsername(token, username);

        if (userDTO != null && userDTO.getUsername() != null) {
            return new AuthenticatedUser(userDTO.getUsername(), userDTO.getRole());
        }

        throw new ConflictException("Usuário(a) não encontrado(a) " + username);
    }

    public SupplierDTO addSupplier(SupplierDTO dto) {

        existBySupplierName(dto.getSupplierName());

        Supplier entity = supplierConverter.supplierEntity(dto);

        return supplierConverter.supplierDTO(supplierRepository.save(entity));

    }

    public void existBySupplierName(String username) {

        try {

            boolean exist = supplierRepository.existsBySupplierName(username);

            if (exist) {
                throw new ConflictException("Fornecedor já cadastrado " + username);
            }

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public List<SupplierDTO> filterAllSupplies() {

        try {

            return supplierConverter.supplierDTOList(
                    supplierRepository.findAll()
            );
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public List<SupplierDTO> filterBySupplierName(String name) {

        try {

            return supplierConverter.supplierDTOList(
                    supplierRepository.findBySupplierNameContainsIgnoreCase(name)
            );
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public SupplierDTO removeSupplier(String token, String id) {

        AuthenticatedUser user = userAuthenticated(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN;

        if (!permitted) {
            throw new ConflictException("OPSS! Você não tem PERMISSÃO para excluir fornecedor.");
        }

        Supplier entity = supplierRepository.findById(id).orElseThrow(
                () -> new ConflictException("Fornecedor não encontrado")
        );

        supplierRepository.deleteById(id);

        return supplierConverter.supplierDTO(entity);
    }

    public SupplierDTO updateSupplier(String token, String id, SupplierDTO dto) {

        AuthenticatedUser user = userAuthenticated(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN;

        if (!permitted) {
            throw new ConflictException("OPSS! Você não tem PERMISSÃO para editar fornecedor.");
        }

        Supplier entityEdit = supplierRepository.findById(id).orElseThrow(
                () -> new ConflictException("Fornecedor não encontrado")
        );

        supplierUpdateConverter.supplierUpdate(dto, entityEdit);

        return supplierConverter.supplierDTO(supplierRepository.save(entityEdit));
    }

}
