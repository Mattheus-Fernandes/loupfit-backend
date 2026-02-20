package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.asset.out.AssetResponse;
import com.loupfit.bffservice.business.record.asset.in.AssetRequest;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "asset-service", url = "${asset.url}", configuration = FeignConfig.class)
public interface AssetClient {


    @PostMapping("/assets")
    AssetResponse saveAsset(@RequestHeader("Authorization") String token, @RequestBody AssetRequest request);

    @GetMapping("/assets")
    List<AssetResponse> findAllAssets(@RequestHeader("Authorization") String token);

    @GetMapping("/assets/by-username")
    List<AssetResponse> findAssetByUsername(@RequestHeader("Authorization") String token, @RequestParam String username);

    @DeleteMapping("/assets/{id}")
    AssetResponse deleteAsset(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/assets/{id}")
    AssetResponse updateAsset(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody AssetRequest request
    );
}
