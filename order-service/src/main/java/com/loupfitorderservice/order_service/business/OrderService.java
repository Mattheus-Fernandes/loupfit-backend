package com.loupfitorderservice.order_service.business;

import com.loupfitorderservice.order_service.business.dto.OrderDTO;
import com.loupfitorderservice.order_service.business.dto.product.ProductDTO;
import com.loupfitorderservice.order_service.business.dto.product.ProductUpdateStockSalesDTO;
import com.loupfitorderservice.order_service.business.dto.user.UserDTO;
import com.loupfitorderservice.order_service.business.mapper.OrderConverter;
import com.loupfitorderservice.order_service.infrastructure.entity.Order;
import com.loupfitorderservice.order_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitorderservice.order_service.infrastructure.repository.OrderRepository;
import com.loupfitorderservice.order_service.infrastructure.security.client.UserClient;
import com.loupfitorderservice.order_service.infrastructure.security.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserClient userClient;
    private final ProductClient productClient;
    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;

    private UserDTO userAuthenticated(String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserDTO user = userClient.getUserByUsername(token, username);

        if (user != null && user.getUsername() != null) {
            return new UserDTO(user.getUsername(), user.getRole());
        }

        throw new ConflictExcpetion("Usuário(a) não encontrado(a) " + username);

    }

    public OrderDTO processSale(String token, OrderDTO dto) {

        UserDTO user = userAuthenticated(token);
        dto.setSoldBy(user.getUsername());

        ProductDTO product = productClient.getProductById(token, dto.getProductId());

        if (product == null) {
            throw new ConflictExcpetion("Produto não encontrado");
        }

        if (product.getStock() < dto.getQuantity()) {
            throw new ConflictExcpetion("A venda nao pode ser maior do que o estoque");
        }

        // Update Stock
        productClient.updateInventory(token, product.getId(),
                new ProductUpdateStockSalesDTO(dto.getQuantity(), "decrease", "STOCK")
        );

        // Update Sale
        productClient.updateInventory(token, product.getId(),
                new ProductUpdateStockSalesDTO(dto.getQuantity(), "increase", "SALES")
        );

        dto.setOrderId("#" + UUID.randomUUID().toString());
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
        dto.setColor(product.getColor());
        dto.setSize(product.getSize());

        Order newOrder = orderConverter.orderEntity(dto);

        return orderConverter.orderDTO(orderRepository.save(newOrder));

    }

    public List<OrderDTO> filterAllSales() {

        try {
            return orderConverter.ordersListDTO(
                    orderRepository.findAll()
            );
        } catch (ConflictExcpetion e) {
            throw new ConflictExcpetion(e.getMessage());
        }
    }
}
