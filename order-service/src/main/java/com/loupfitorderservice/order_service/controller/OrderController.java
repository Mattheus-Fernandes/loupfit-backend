package com.loupfitorderservice.order_service.controller;

import com.loupfitorderservice.order_service.business.OrderService;
import com.loupfitorderservice.order_service.business.record.order.in.OrderRequest;
import com.loupfitorderservice.order_service.business.record.order.out.OrderResponse;
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
    public ResponseEntity<OrderResponse> saveOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderRequest request
    ) {
        return ResponseEntity.ok(orderService.processSale(token, request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllSales() {
        return ResponseEntity.ok(orderService.filterAllSales());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteSale(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(orderService.removeSale(id));
    }
}
