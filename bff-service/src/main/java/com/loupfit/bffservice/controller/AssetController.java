package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.AssetService;
import com.loupfit.bffservice.business.record.asset.in.AssetRequest;
import com.loupfit.bffservice.business.record.asset.out.AssetResponse;
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
    public ResponseEntity<List<AssetResponse>> findAllAssets(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(assetService.filterAllAssets(token));
    }

    @GetMapping("/by-username")
    public ResponseEntity<List<AssetResponse>> findAssetByUsername(@RequestHeader("Authorization") String token, @RequestParam String username) {
        return ResponseEntity.ok(assetService.assetAddedByCreatedBy(token, username));
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
