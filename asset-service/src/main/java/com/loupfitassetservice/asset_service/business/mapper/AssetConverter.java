package com.loupfitassetservice.asset_service.business.mapper;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel= "spring")
public interface AssetConverter {

    Asset assetEntity(AssetDTO assetDTO);

    AssetDTO assetDTO(Asset asset);

    List<Asset> assetEntitiesList(List<AssetDTO> assetDTOList);

    List<AssetDTO> assetDTOList(List<Asset> assetList);

}
