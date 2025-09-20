package com.loupfitconsumablesservice.consumables_service.business.mapper;

import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesDTO;
import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsumablesUpdateConverter {

    void consumableUpdate(ConsumablesDTO consumablesDTO, @MappingTarget Consumables consumables);
}
