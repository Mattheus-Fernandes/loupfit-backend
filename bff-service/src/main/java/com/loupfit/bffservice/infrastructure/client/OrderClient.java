package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.record.order.in.OrderRequest;
import com.loupfit.bffservice.business.record.order.out.OrderResponse;
import com.loupfit.bffservice.infrastructure.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service", url = "${order.url}", configuration = FeignConfig.class)
public interface OrderClient {

    @PostMapping("/orders")
    OrderResponse saveOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderRequest request
    );

    @GetMapping("/orders")
    List<OrderResponse> findAllSales(@RequestHeader("Authorization") String token);

    @DeleteMapping("/orders/{id}")
    OrderResponse deleteSale(@RequestHeader("Authorization") String token, @PathVariable String id);
}
