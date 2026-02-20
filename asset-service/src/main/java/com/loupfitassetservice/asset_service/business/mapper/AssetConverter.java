package com.loupfitassetservice.asset_service.business.mapper;

import com.loupfitassetservice.asset_service.business.record.asset.in.AssetRequest;
import com.loupfitassetservice.asset_service.business.record.asset.out.AssetResponse;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel= "spring")
public interface AssetConverter {

    Asset toEntity(AssetRequest request);

    AssetResponse toResponse(Asset entity);

    List<AssetResponse> toResponseList(List<Asset> entities);

}
