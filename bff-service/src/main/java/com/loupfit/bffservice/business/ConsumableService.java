package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.in.ConsumablesQuantityDTO;
import com.loupfit.bffservice.business.dto.out.ConsumablesDTO;
import com.loupfit.bffservice.infrastructure.client.ConsumableClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumableService {

    private final ConsumableClient consumableClient;

    public ConsumablesDTO addConsumable(String token, ConsumablesDTO consumablesDTO) {
        return consumableClient.saveConsumables(token, consumablesDTO);
    }

    public List<ConsumablesDTO> filterAllConsumables(String token) {
        return consumableClient.findAllConsumables(token);
    }

    public ConsumablesDTO removeConsumable(String token, String id) {
        return consumableClient.deleteConsumables(token, id);
    }

    public ConsumablesDTO editConsumable(String token, String id, ConsumablesDTO consumablesDTO) {
        return consumableClient.updateConsumables(token, consumablesDTO, id);
    }

    public ConsumablesDTO editQuantityConsumable(String token, String id, ConsumablesQuantityDTO consumablesQuantityDTO) {
        return consumableClient.updateConsumablesQuantity(token, consumablesQuantityDTO, id);
    }
}
