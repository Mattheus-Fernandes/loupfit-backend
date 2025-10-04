package com.loupfitassetservice.asset_service.business;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.business.dto.AuthenticatedUserDTO;
import com.loupfitassetservice.asset_service.business.dto.UserDTO;
import com.loupfitassetservice.asset_service.business.mapper.AssetConverter;
import com.loupfitassetservice.asset_service.business.mapper.AssetUpdateConverter;
import com.loupfitassetservice.asset_service.infrastructure.client.UserClient;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import com.loupfitassetservice.asset_service.infrastructure.enums.UserRole;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitassetservice.asset_service.infrastructure.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    private AuthenticatedUserDTO userAuthenticated(String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserDTO userDTO = userClient.getUserByUsername(token, username);

        if (userDTO != null && userDTO.getUsername() != null) {
            return new AuthenticatedUserDTO(userDTO.getUsername(), userDTO.getRole());
        }

        throw new ConflictExcpetion("Usuário(a) não encontrado(a) " + username);

    }

    public AssetDTO addAsset(String token, AssetDTO assetDTO) {

        AuthenticatedUserDTO user = userAuthenticated(token);

        existAsset(assetDTO.getAssetName());

        assetDTO.setCreatedBy(user.getUsername());

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

        AuthenticatedUserDTO user = userAuthenticated(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN;

        if (!permitted) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para excluir o equipamento.");
        }

        Asset assetDelete = assetRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Equipamento não encontrado")
        );

        assetRepository.delete(assetDelete);

        return assetConverter.assetDTO(assetDelete);

    }

    public AssetDTO editAsset(String token, String id, AssetDTO assetDTO) {

        AuthenticatedUserDTO user = userAuthenticated(token);

        boolean permitted = user.getRole() == UserRole.OWNER || user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.EDITOR;

        if (!permitted) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar o equipamento.");
        }

        Asset assetEdit = assetRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Equipamento não encontrado")
        );

        assetUpdateConverter.assetUpdate(assetDTO, assetEdit);

        return assetConverter.assetDTO(assetRepository.save(assetEdit));


    }
}
