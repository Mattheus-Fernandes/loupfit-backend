package com.loupfitassetservice.asset_service.business;

import com.loupfitassetservice.asset_service.business.mapper.AssetConverter;
import com.loupfitassetservice.asset_service.business.mapper.AssetUpdateConverter;
import com.loupfitassetservice.asset_service.business.record.asset.in.AssetRequest;
import com.loupfitassetservice.asset_service.business.record.asset.out.AssetResponse;
import com.loupfitassetservice.asset_service.business.record.user.out.UserAuthenticatedResponse;
import com.loupfitassetservice.asset_service.business.record.user.out.UserResponse;
import com.loupfitassetservice.asset_service.infrastructure.client.UserClient;
import com.loupfitassetservice.asset_service.infrastructure.entity.Asset;
import com.loupfitassetservice.asset_service.infrastructure.enums.UserRole;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ConflictException;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ForbiddenException;
import com.loupfitassetservice.asset_service.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfitassetservice.asset_service.infrastructure.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetConverter assetConverter;
    private final UserClient userClient;
    private final AssetUpdateConverter assetUpdateConverter;

    private UserAuthenticatedResponse userAuthenticated(String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserResponse user = userClient.getUserByUsername(token, username);

        if (user != null && user.username() != null) {
            return new UserAuthenticatedResponse(user.username(), user.role());
        }

        throw new ResourceNotFoundException("Usuário(a) não encontrado(a) " + username);

    }

    public AssetResponse addAsset(String token, AssetRequest request) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        existAsset(request.name());

        AssetRequest data = new AssetRequest(
                request.name(),
                request.description(),
                request.quantity(),
                request.costValue(),
                request.placePurchase(),
                user.username()
        );

        Asset asset = assetConverter.toEntity(data);

        return assetConverter.toResponse(assetRepository.save(asset));

    }

    public void existAsset(String assetName) {

        try {
            boolean exist = assetRepository.existsByName(assetName);

            if (exist) {
                throw new ConflictException("Equipamento já cadastrado " + assetName);
            }

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public List<AssetResponse> filterAllAssets() {
        return assetConverter.toResponseList(assetRepository.findAll());
    }

    public List<AssetResponse> assetAddedByCreatedBy(String username) {
        return assetConverter.toResponseList(assetRepository.findByCreatedBy(username));
    }

    public AssetResponse removeAsset(String token, String id) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        boolean permitted = user.role() == UserRole.OWNER || user.role() == UserRole.ADMIN;

        if (!permitted) {
            throw new ForbiddenException("OPSS! Você não tem PERMISSÃO para excluir o equipamento.");
        }

        Asset assetDelete = assetRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Equipamento não encontrado")
        );

        assetRepository.delete(assetDelete);

        return assetConverter.toResponse(assetDelete);

    }

    public AssetResponse editAsset(String token, String id, AssetRequest request) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        boolean permitted = user.role() == UserRole.OWNER || user.role() == UserRole.ADMIN || user.role() == UserRole.EDITOR;

        if (!permitted) {
            throw new ForbiddenException("OPSS! Você não tem PERMISSÃO para editar o equipamento.");
        }

        AssetRequest data = new AssetRequest(
                request.name(),
                request.description(),
                request.quantity(),
                request.costValue(),
                request.placePurchase(),
                request.createdBy()
        );

        Asset assetEntity = assetRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Equipamento não encontrado")
        );

        Asset editAsset = assetUpdateConverter.doUpdate(data, assetEntity);

        return assetConverter.toResponse(assetRepository.save(editAsset));

    }
}
