package com.loupfitconsumablesservice.consumables_service.business;

import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesDTO;
import com.loupfitconsumablesservice.consumables_service.business.dto.UserDTO;
import com.loupfitconsumablesservice.consumables_service.business.mapper.ComsumablesConverter;
import com.loupfitconsumablesservice.consumables_service.infrastructure.client.UserClient;
import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitconsumablesservice.consumables_service.infrastructure.repository.ConsumablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumablesService {

    private final ConsumablesRepository consumablesRepository;
    private final UserClient userClient;
    private final ComsumablesConverter consumablesConverter;

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
}
