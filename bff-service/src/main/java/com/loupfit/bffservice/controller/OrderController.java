package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.OrderService;
import com.loupfit.bffservice.business.record.order.in.OrderRequest;
import com.loupfit.bffservice.business.record.order.out.OrderResponse;
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
    public ResponseEntity<List<OrderResponse>> findAllSales(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(orderService.filterAllSales(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteSale(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(orderService.removeSale(token, id));
    }
}
