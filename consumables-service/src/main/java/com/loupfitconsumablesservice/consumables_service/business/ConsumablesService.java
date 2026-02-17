package com.loupfitconsumablesservice.consumables_service.business;

import com.loupfitconsumablesservice.consumables_service.business.mapper.ComsumablesConverter;
import com.loupfitconsumablesservice.consumables_service.business.mapper.ConsumablesUpdateConverter;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.in.ConsumableQuantityRequest;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.in.ConsumableRequest;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.out.ConsumableResponse;
import com.loupfitconsumablesservice.consumables_service.business.record.user.out.UserAuthenticatedResponse;
import com.loupfitconsumablesservice.consumables_service.business.record.user.out.UserResponse;
import com.loupfitconsumablesservice.consumables_service.infrastructure.client.UserClient;
import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import com.loupfitconsumablesservice.consumables_service.infrastructure.enums.UserRole;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ConflictException;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ForbiddenException;
import com.loupfitconsumablesservice.consumables_service.infrastructure.repository.ConsumablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumablesService {

    private final ConsumablesRepository consumablesRepository;
    private final UserClient userClient;
    private final ComsumablesConverter consumablesConverter;
    private final ConsumablesUpdateConverter consumablesUpdateConverter;

    private UserAuthenticatedResponse userAuthenticated(String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserResponse user = userClient.findUserByUsername(token, username);

        if (user != null && user.username() != null) {
            return new UserAuthenticatedResponse(user.username(), user.role());
        }

        throw new ConflictException("Usuário(a) não encontrado(a) " + username);
    }

    public ConsumableResponse addConsumable(String token, ConsumableRequest request) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        ConsumableRequest data = new ConsumableRequest(
                request.name(),
                request.description(),
                request.costValue(),
                request.quantity(),
                request.placePurchase(),
                request.purchaseLink(),
                user.username()
        );

        Consumables comsumable = consumablesConverter.toEntity(data);

        return consumablesConverter.toResponse(consumablesRepository.save(comsumable));
    }

    public List<ConsumableResponse> filterAllConsumables() {
        return consumablesConverter.toResponseList(consumablesRepository.findAll());
    }

    public ConsumableResponse removeConsumable(String token, String id) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        hasPermission(user, "DELETE");

        Consumables consumableDelete = consumablesRepository.findById(id).orElseThrow(
                () -> new ConflictException("Consumível não encontrado")
        );

        consumablesRepository.delete(consumableDelete);

        return consumablesConverter.toResponse(consumableDelete);
    }

    public ConsumableResponse editConsumable(String token, String id, ConsumableRequest request) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        hasPermission(user, "PUT");

        ConsumableRequest data = new ConsumableRequest(
                request.name(),
                request.description(),
                request.costValue(),
                request.quantity(),
                request.placePurchase(),
                request.purchaseLink(),
                user.username()
        );

        Consumables consumableEntity = consumablesRepository.findById(id).orElseThrow(
                () -> new ConflictException("Consumível não encontrado")
        );

        Consumables consumableEdit = consumablesUpdateConverter.doUpdate(data, consumableEntity);


        return consumablesConverter.toResponse(consumablesRepository.save(consumableEdit));
    }

    public ConsumableResponse editQuantityConsumable(String token, String id, ConsumableQuantityRequest request) {

        UserAuthenticatedResponse user = userAuthenticated(token);

        hasPermission(user, "PATCH");

        Consumables consumable = consumablesRepository.findById(id).orElseThrow(
                () -> new ConflictException("Consumível não encontrado")
        );

        if ("DECREASE".equalsIgnoreCase(request.operation())) {

            if (consumable.getQuantity() < request.quantity()) {
                throw new ConflictException("Estoque insuficiente para realizar a operação");
            }

            consumable.setQuantity(consumable.getQuantity() - request.quantity());
        }

        if ("INCREASE".equalsIgnoreCase(request.operation())) {
            consumable.setQuantity(consumable.getQuantity() + request.quantity());
        }

        return consumablesConverter.toResponse(consumablesRepository.save(consumable));
    }

    private void hasPermission(UserAuthenticatedResponse user, String method) {
        boolean permitted = user.role() == UserRole.OWNER || user.role() == UserRole.ADMIN || user.role() == UserRole.EDITOR;

        String methodType = "DELETE".equals(method) ? "excluir" : "editar";

        if (!permitted) {
            throw new ForbiddenException("OPSS! Você não tem PERMISSÃO para " + methodType + " consumíveis.");
        }
    }
}
