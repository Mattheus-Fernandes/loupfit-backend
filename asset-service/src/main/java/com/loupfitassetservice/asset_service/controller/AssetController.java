package com.loupfitassetservice.asset_service.controller;

import com.loupfitassetservice.asset_service.business.AssetService;
import com.loupfitassetservice.asset_service.business.dto.AssetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetDTO> saveAsset(@RequestBody AssetDTO assetDTO) {
        return ResponseEntity.ok(assetService.addAsset(assetDTO));
    }
}
