package com.loupfitassetservice.asset_service.business;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.business.dto.UserDTO;
import com.loupfitassetservice.asset_service.business.mapper.AssetConverter;
import com.loupfitassetservice.asset_service.infrastructure.client.UserClient;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
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

    private UserDTO getCurrentUser(String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new ConflictExcpetion("Usuário não autenticado");
        }

        String username = auth.getName();

        return userClient.findUserByUsername(token, username);

    }

    public AssetDTO addAsset(String token, AssetDTO assetDTO) {
        UserDTO userDTO = getCurrentUser(token);

        existAsset(assetDTO.getAssetName());

        assetDTO.setCreatedBy(userDTO.getUsername());

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

}
