package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.AssetService;
import com.loupfit.bffservice.business.dto.out.AssetDTO;
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
    public ResponseEntity<List<AssetDTO>> findAllAssets(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(assetService.filterAllAssets(token));
    }

    @GetMapping("/by-username")
    public ResponseEntity<List<AssetDTO>> findAssetByUsername(@RequestHeader("Authorization") String token, @RequestParam String username) {
        return ResponseEntity.ok(assetService.assetAddedByCreatedBy(token, username));
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
