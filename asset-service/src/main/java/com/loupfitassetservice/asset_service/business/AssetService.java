package com.loupfitassetservice.asset_service.business;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.business.dto.EmployeeDTO;
import com.loupfitassetservice.asset_service.business.dto.UserDTO;
import com.loupfitassetservice.asset_service.business.mapper.AssetConverter;
import com.loupfitassetservice.asset_service.business.mapper.AssetUpdateConverter;
import com.loupfitassetservice.asset_service.infrastructure.client.UserClient;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitassetservice.asset_service.infrastructure.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetConverter assetConverter;
    private final UserClient userClient;
    private final AssetUpdateConverter assetUpdateConverter;

    private UserDTO getCurrentUser(String token, String username) {
        return userClient.getUserByUsername(token, username);
    }

    private EmployeeDTO getCurrentEmployee(String token, String username) {
        return userClient.getEmployeeByUsername(token, username);
    }

    private String authenticated(String token) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            UserDTO userDTO = getCurrentUser(token, username);
            if (userDTO != null && userDTO.getUsername() != null) {
                return userDTO.getUsername();
            }
        } catch (Exception e) {
            System.out.println("Proprietário(a) não encontrado(a) via Feign Client " + e.getMessage());
        }

        try {
            EmployeeDTO employeeDTO = getCurrentEmployee(token, username);
            if (employeeDTO != null && employeeDTO.getUsername() != null) {
                return employeeDTO.getUsername();
            }
        } catch (Exception e) {
            System.out.println("Funcionário(a) não encontrado(a) via Feign Client " + e.getMessage());
        }

        throw new ConflictExcpetion("Usuário(a) ou funcionário(a) não encontrado(a) " + username);

    }

    public AssetDTO addAsset(String token, AssetDTO assetDTO) {

        String createdBy = authenticated(token);

        existAsset(assetDTO.getAssetName());

        assetDTO.setCreatedBy(createdBy);

        Asset assetEntity = assetConverter.assetEntity(assetDTO);

        return assetConverter.assetDTO(assetRepository.save(assetEntity));
    }

    public void existAsset(String assetName) {

        try {
            boolean exist = assetRepository.existsByAssetName(assetName);

            if (exist) {
                throw new ConflictExcpetion("Equipamento já cadastrado " + assetName);
            }

        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }

    public List<AssetDTO> filterAllAssets() {
        return assetConverter.assetDTOList(assetRepository.findAll());
    }

    public List<AssetDTO> assetAddedByCreatedBy(String username) {
        return assetConverter.assetDTOList(assetRepository.findByCreatedBy(username));
    }

    public AssetDTO removeAsset(String token, String id) {

        UserDTO userDTO = getCurrentUser(token);

        if (!userDTO.getRole().equals(1)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para excluir o equipamento.");
        }

        Asset assetDelete = assetRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Equipamento não encontrado")
        );

        assetRepository.delete(assetDelete);

        return assetConverter.assetDTO(assetDelete);

    }

    public AssetDTO editAsset(String token, String id, AssetDTO assetDTO) {

        UserDTO userDTO = getCurrentUser(token);

        if (!userDTO.getRole().equals(1)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar o equipamento.");
        }

        Asset assetEdit = assetRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Equipamento não encontrado")
        );

        assetUpdateConverter.assetUpdate(assetDTO, assetEdit);

        return assetConverter.assetDTO(assetRepository.save(assetEdit));


    }
}
