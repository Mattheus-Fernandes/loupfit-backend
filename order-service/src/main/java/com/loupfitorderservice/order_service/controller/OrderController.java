package com.loupfitorderservice.order_service.controller;

import com.loupfitorderservice.order_service.business.OrderService;
import com.loupfitorderservice.order_service.business.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderDTO dto
    ) {
        return ResponseEntity.ok(orderService.processSale(token, dto));
    }
}
