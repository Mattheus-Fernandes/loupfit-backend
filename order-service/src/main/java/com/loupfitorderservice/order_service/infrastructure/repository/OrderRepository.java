package com.loupfitorderservice.order_service.infrastructure.repository;

import com.loupfitorderservice.order_service.infrastructure.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
