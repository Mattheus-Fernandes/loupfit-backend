package com.loupfitassetservice.asset_service.infrastructure.repository;

import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

    boolean existsByAssetName(String assetName);
}
