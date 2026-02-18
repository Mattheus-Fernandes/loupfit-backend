package com.loupfitorderservice.order_service.business;

import com.loupfitorderservice.order_service.business.record.order.in.OrderRequest;
import com.loupfitorderservice.order_service.business.record.order.out.OrderResponse;
import com.loupfitorderservice.order_service.business.mapper.OrderConverter;
import com.loupfitorderservice.order_service.business.record.product.in.ProductUpdateStockSalesRequest;
import com.loupfitorderservice.order_service.business.record.product.out.ProductResponse;
import com.loupfitorderservice.order_service.business.record.user.out.UserResponse;
import com.loupfitorderservice.order_service.infrastructure.entity.Order;
import com.loupfitorderservice.order_service.infrastructure.exceptions.ConflictExcpetion;
import com.loupfitorderservice.order_service.infrastructure.exceptions.ResourceNotFoundException;
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

    private UserResponse userAuthenticated(String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserResponse user = userClient.getUserByUsername(token, username);

        if (user != null && user.username() != null) {
            return new UserResponse(user.username(), user.role());
        }

        throw new ResourceNotFoundException("Usuário(a) não encontrado(a) " + username);

    }

    public OrderResponse processSale(String token, OrderRequest request) {

        UserResponse user = userAuthenticated(token);

        ProductResponse product = productClient.getProductById(token, request.productId());

        if (product == null) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        if (product.stock() < request.quantity()) {
            throw new ConflictExcpetion("A venda não pode ser maior do que o estoque");
        }

        OrderRequest data = new OrderRequest(
                "#" + UUID.randomUUID().toString(),
                product.id(),
                product.name(),
                product.imageUrl(),
                request.quantity(),
                product.price().multiply(BigDecimal.valueOf(request.quantity())),
                product.size(),
                product.color(),
                user.username(),
                request.paymentMethod()
        );

        Order order = orderConverter.toEntity(data);

        // Update Stock
        productClient.updateInventory(token, product.id(),
                new ProductUpdateStockSalesRequest(request.quantity(), "decrease", "STOCK")
        );

        // Update Sale
        productClient.updateInventory(token, product.id(),
                new ProductUpdateStockSalesRequest(request.quantity(), "increase", "SALES")
        );

        return orderConverter.toResponse(orderRepository.save(order));

    }

    public List<OrderResponse> filterAllSales() {

        try {
            return orderConverter.toResponseList(
                    orderRepository.findAll()
            );
        } catch (ConflictExcpetion e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public OrderResponse removeSale(String id) {

        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Registro de venda não encontrada")
        );

        orderRepository.deleteById(id);

        return orderConverter.toResponse(order);
    }
}
