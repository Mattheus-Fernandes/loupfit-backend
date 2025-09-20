package com.loupfitconsumablesservice.consumables_service.infrastructure.repository;

import com.loupfitconsumablesservice.consumables_service.infrastructure.entity.Consumables;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumablesRepository extends MongoRepository<Consumables, String> {
}
