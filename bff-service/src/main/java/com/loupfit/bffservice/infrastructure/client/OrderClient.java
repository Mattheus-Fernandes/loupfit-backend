package com.loupfit.bffservice.infrastructure.client;

import com.loupfit.bffservice.business.dto.out.OrderDTO;
import com.loupfit.bffservice.infrastructure.client.config.OrderClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service", url = "${order.url}", configuration = OrderClientConfig.class)
public interface OrderClient {

    @PostMapping("/orders")
    OrderDTO saveOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderDTO dto
    );

    @GetMapping("/orders")
    List<OrderDTO> findAllSales(@RequestHeader("Authorization") String token);

    @DeleteMapping("/orders/{id}")
    OrderDTO deleteSale(@RequestHeader("Authorization") String token, @PathVariable String id);
}
