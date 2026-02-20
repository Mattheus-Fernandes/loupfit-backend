package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.record.order.in.OrderRequest;
import com.loupfit.bffservice.business.record.order.out.OrderResponse;
import com.loupfit.bffservice.infrastructure.client.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;

    public OrderResponse processSale(String token, OrderRequest request) {
        return orderClient.saveOrder(token, request);
    }

    public List<OrderResponse> filterAllSales(String token) {
        return orderClient.findAllSales(token);
    }

    public OrderResponse removeSale(String token, String id) {
        return orderClient.deleteSale(token, id);
    }
}
