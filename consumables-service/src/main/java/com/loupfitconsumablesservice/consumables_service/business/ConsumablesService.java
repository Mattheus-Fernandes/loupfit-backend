package com.loupfitconsumablesservice.consumables_service.business;

import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesDTO;
import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesQuantityDTO;
import com.loupfitconsumablesservice.consumables_service.business.dto.UserDTO;
import com.loupfitconsumablesservice.consumables_service.business.mapper.ComsumablesConverter;
import com.loupfitconsumablesservice.consumables_service.business.mapper.ConsumablesUpdateConverter;
import com.loupfitconsumablesservice.consumables_service.infrastructure.client.UserClient;
import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ConflictExcpetion;
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

    private UserDTO getCurrentUser(String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new ConflictExcpetion("Usuário não autenticado");
        }

        String username = auth.getName();

        return userClient.findUserByUsername(token, username);
    }

    public ConsumablesDTO addConsumable(String token, ConsumablesDTO consumablesDTO) {
        UserDTO userDTO = getCurrentUser(token);

        consumablesDTO.setCreatedby(userDTO.getUsername());

        Consumables consumables = consumablesConverter.comsumablesEntity(consumablesDTO);

        return consumablesConverter.consumablesDTO(consumablesRepository.save(consumables));

    }

    public List<ConsumablesDTO> filterAllConsumables() {
        return consumablesConverter.consumablesDTOList(consumablesRepository.findAll());
    }

    public ConsumablesDTO removeConsumable(String token, String id) {
        UserDTO userDTO = getCurrentUser(token);

        if (!userDTO.getRole().equals(1)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para excluir consumíveis.");
        }

        Consumables consumableDelete = consumablesRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Consumivel não encontrado")
        );

        consumablesRepository.delete(consumableDelete);

        return consumablesConverter.consumablesDTO(consumableDelete);
    }

    public ConsumablesDTO editConsumable(String token, String id, ConsumablesDTO consumablesDTO) {

        UserDTO userDTO = getCurrentUser(token);

        if (!userDTO.getRole().equals(1)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar consumíveis.");
        }

        Consumables consumableEdit = consumablesRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Consumível não encontrado")
        );

        consumablesUpdateConverter.consumableUpdate(consumablesDTO, consumableEdit);

        return consumablesConverter.consumablesDTO(consumablesRepository.save(consumableEdit));
    }

    public ConsumablesDTO editQuantityConsumable(String token, String id, ConsumablesQuantityDTO consumablesQuantityDTO) {

        UserDTO userDTO = getCurrentUser(token);

        if (!userDTO.getRole().equals(1)) {
            throw new ConflictExcpetion("OPSS! Você não tem PERMISSÃO para editar consumíveis.");
        }

        Consumables consumableQuantity = consumablesRepository.findById(id).orElseThrow(
                () -> new ConflictExcpetion("Consumível não encontrado")
        );

        if ("DECREASE".equalsIgnoreCase(consumablesQuantityDTO.getOperation())) {

            if (consumableQuantity.getQuantity() < consumablesQuantityDTO.getQuantity()) {
                throw new ConflictExcpetion("Estoque insuficiente para realizar a operação");
            }

            consumableQuantity.setQuantity(consumableQuantity.getQuantity() - consumablesQuantityDTO.getQuantity());
        }

        if ("INCREASE".equalsIgnoreCase(consumablesQuantityDTO.getOperation())) {
            consumableQuantity.setQuantity(consumableQuantity.getQuantity() + consumablesQuantityDTO.getQuantity());
        }

        return consumablesConverter.consumablesDTO(consumablesRepository.save(consumableQuantity));
    }
}
