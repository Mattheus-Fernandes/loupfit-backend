package com.loupfitorderservice.order_service.controller;

import com.loupfitorderservice.order_service.business.OrderService;
import com.loupfitorderservice.order_service.business.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
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

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAllSales() {
        return ResponseEntity.ok(orderService.filterAllSales());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteSale(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(orderService.removeSale(id));
    }
}
