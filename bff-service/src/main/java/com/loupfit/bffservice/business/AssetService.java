package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.asset.in.AssetRequest;
import com.loupfit.bffservice.business.record.asset.out.AssetResponse;
import com.loupfit.bffservice.infrastructure.client.AssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetClient assetClient;

    public AssetResponse addAsset(String token, AssetRequest request) {
        return assetClient.saveAsset(token, request);
    }

    public List<AssetResponse> filterAllAssets(String token) {
        return assetClient.findAllAssets(token);
    }

    public List<AssetResponse> assetAddedByCreatedBy(String token, String username) {
        return assetClient.findAssetByUsername(token, username);
    }

    public AssetResponse removeAsset(String token, String id) {
        return assetClient.deleteAsset(token, id);
    }

    public AssetResponse editAsset(String token, String id, AssetRequest request) {
        return assetClient.updateAsset(token, id, request);
    }
}
