package com.loupfitassetservice.asset_service.controller;

import com.loupfitassetservice.asset_service.business.AssetService;
import com.loupfitassetservice.asset_service.business.record.asset.in.AssetRequest;
import com.loupfitassetservice.asset_service.business.record.asset.out.AssetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetResponse> saveAsset(@RequestHeader("Authorization") String token, @RequestBody AssetRequest request) {
        return ResponseEntity.ok(assetService.addAsset(token, request));
    }

    @GetMapping
    public ResponseEntity<List<AssetResponse>> findAllAssets() {
        return ResponseEntity.ok(assetService.filterAllAssets());
    }

    @GetMapping("/by-username")
    public ResponseEntity<List<AssetResponse>> findAssetByUsername(@RequestParam String username) {
        return ResponseEntity.ok(assetService.assetAddedByCreatedBy(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AssetResponse> deleteAsset(@RequestHeader("Authorization") String token, @PathVariable String id) {
        return ResponseEntity.ok(assetService.removeAsset(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetResponse> updateAsset(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody AssetRequest request
    ) {
        return  ResponseEntity.ok(assetService.editAsset(token, id, request));
    }
}
