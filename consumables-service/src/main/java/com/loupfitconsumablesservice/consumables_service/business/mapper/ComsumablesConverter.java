package com.loupfitconsumablesservice.consumables_service.business.mapper;

import com.loupfitconsumablesservice.consumables_service.business.dto.ConsumablesDTO;
import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComsumablesConverter {

    Consumables comsumablesEntity(ConsumablesDTO consumablesDTO);

    ConsumablesDTO consumablesDTO(Consumables consumables);

    List<Consumables> consumablesList(List<ConsumablesDTO> consumablesDTOList);

    List<ConsumablesDTO> consumablesDTOList(List<Consumables> consumablesList);
}
