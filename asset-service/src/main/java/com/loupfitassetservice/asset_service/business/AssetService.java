package com.loupfitassetservice.asset_service.business;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.business.mapper.AssetConverter;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitassetservice.asset_service.infrastructure.repository.AssetRepository;
import com.loupfitassetservice.asset_service.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetConverter assetConverter;
    private final JwtUtil jwtUtil;

    public AssetDTO addAsset(AssetDTO assetDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        existAsset(assetDTO.getAssetName());

        assetDTO.setCreatedBy(username);

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

}
