package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.out.AssetDTO;
import com.loupfit.bffservice.infrastructure.client.config.AssetClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "asset-service", url = "${asset.url}", configuration = AssetClientConfig.class)
public interface AssetClient {


    @PostMapping("/assets")
    AssetDTO saveAsset(@RequestHeader("Authorization") String token, @RequestBody AssetDTO assetDTO);

    @GetMapping("/assets")
    List<AssetDTO> findAllAssets(@RequestHeader("Authorization") String token);

    @GetMapping("/assets/by-username")
    List<AssetDTO> findAssetByUsername(@RequestHeader("Authorization") String token, @RequestParam String username);

    @DeleteMapping("/assets/{id}")
    AssetDTO deleteAsset(@RequestHeader("Authorization") String token, @PathVariable String id);

    @PutMapping("/assets/{id}")
    AssetDTO updateAsset(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody AssetDTO assetDTO
    );
}
