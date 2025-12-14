package com.loupfit.bffservice.business;

import com.loupfit.bffservice.business.dto.out.OrderDTO;
import com.loupfit.bffservice.infrastructure.client.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;

    public OrderDTO processSale(String token, OrderDTO dto) {
        return orderClient.saveOrder(token, dto);
    }

    public List<OrderDTO> filterAllSales(String token) {
        return orderClient.findAllSales(token);
    }

    public OrderDTO removeSale(String token, String id) {
        return orderClient.deleteSale(token, id);
    }
}
