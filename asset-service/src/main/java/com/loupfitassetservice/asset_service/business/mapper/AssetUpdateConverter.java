package com.loupfitassetservice.asset_service.business.mapper;

import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssetUpdateConverter {

    void assetUpdate(AssetDTO assetDTO, @MappingTarget Asset asset);
}
