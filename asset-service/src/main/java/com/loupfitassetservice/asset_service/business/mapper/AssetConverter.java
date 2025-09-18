package com.loupfitassetservice.asset_service.business.mapper;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel= "spring")
public interface AssetConverter {

    Asset assetEntity(AssetDTO assetDTO);

    AssetDTO assetDTO(Asset asset);

}
