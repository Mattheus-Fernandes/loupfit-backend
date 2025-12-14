package com.loupfit.bffservice.controller;

import com.loupfit.bffservice.business.OrderService;
import com.loupfit.bffservice.business.dto.out.OrderDTO;
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
    public ResponseEntity<List<OrderDTO>> findAllSales(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(orderService.filterAllSales(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteSale(
            @RequestHeader("Authorization") String token,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(orderService.removeSale(token, id));
    }
}
