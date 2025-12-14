package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.out.AssetDTO;
import com.loupfit.bffservice.infrastructure.client.AssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetClient assetClient;

    public AssetDTO addAsset(String token, AssetDTO assetDTO) {
        return assetClient.saveAsset(token, assetDTO);
    }

    public List<AssetDTO> filterAllAssets(String token) {
        return assetClient.findAllAssets(token);
    }

    public List<AssetDTO> assetAddedByCreatedBy(String token, String username) {
        return assetClient.findAssetByUsername(token, username);
    }

    public AssetDTO removeAsset(String token, String id) {
        return assetClient.deleteAsset(token, id);
    }

    public AssetDTO editAsset(String token, String id, AssetDTO assetDTO) {
        return assetClient.updateAsset(token, id, assetDTO);
    }
}
