package com.loupfitassetservice.asset_service.controller;

import com.loupfitassetservice.asset_service.business.AssetService;
import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
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
    public ResponseEntity<AssetDTO> saveAsset(@RequestHeader("Authorization") String token, @RequestBody AssetDTO assetDTO) {
        return ResponseEntity.ok(assetService.addAsset(token, assetDTO));
    }

    @GetMapping
    public ResponseEntity<List<AssetDTO>> findAllAssets() {
        return ResponseEntity.ok(assetService.filterAllAssets());
    }

    @GetMapping("/by-username")
    public ResponseEntity<List<AssetDTO>> findAssetByUsername(@RequestParam String username) {
        return ResponseEntity.ok(assetService.assetAddedByCreatedBy(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AssetDTO> deleteAsset(@RequestHeader("Authorization") String token, @PathVariable String id) {
        return ResponseEntity.ok(assetService.removeAsset(token, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> updateAsset(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,
            @RequestBody AssetDTO assetDTO
    ) {
        return  ResponseEntity.ok(assetService.editAsset(token, id, assetDTO));
    }
}
