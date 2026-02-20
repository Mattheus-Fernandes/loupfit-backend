package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.consumable.in.ConsumableQuantityRequest;
import com.loupfit.bffservice.business.record.consumable.in.ConsumableRequest;
import com.loupfit.bffservice.business.record.consumable.out.ConsumableResponse;
import com.loupfit.bffservice.infrastructure.client.ConsumableClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumableService {

    private final ConsumableClient consumableClient;

    public ConsumableResponse addConsumable(String token, ConsumableRequest request) {
        return consumableClient.saveConsumables(token, request);
    }

    public List<ConsumableResponse> filterAllConsumables(String token) {
        return consumableClient.findAllConsumables(token);
    }

    public ConsumableResponse removeConsumable(String token, String id) {
        return consumableClient.deleteConsumables(token, id);
    }

    public ConsumableResponse editConsumable(String token, String id, ConsumableRequest request) {
        return consumableClient.updateConsumables(token, request, id);
    }

    public ConsumableResponse editQuantityConsumable(String token, String id, ConsumableQuantityRequest request) {
        return consumableClient.updateConsumablesQuantity(token, request, id);
    }
}
