package com.loupfitconsumablesservice.consumables_service.business.mapper;

import com.loupfitconsumablesservice.consumables_service.business.record.consumable.in.ConsumableRequest;
import com.loupfitconsumablesservice.consumables_service.business.record.consumable.out.ConsumableResponse;
import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComsumablesConverter {

    Consumables toEntity(ConsumableRequest request);

    ConsumableResponse toResponse(Consumables entity);

    List<ConsumableResponse> toResponseList(List<Consumables> entities);
}
