package com.tradingengine.service.impl;

import com.tradingengine.dto.request.BuyOrderRequest;
import com.tradingengine.dto.request.SellOrderRequest;
import com.tradingengine.dto.response.OrderResponse;
import com.tradingengine.entity.Order;
import com.tradingengine.entity.Stock;
import com.tradingengine.entity.User;
import com.tradingengine.enums.OrderStatus;
import com.tradingengine.enums.OrderType;
import com.tradingengine.exception.ResourceNotFoundException;
import com.tradingengine.repository.OrderRepository;
import com.tradingengine.repository.StockRepository;
import com.tradingengine.repository.UserRepository;
import com.tradingengine.service.MatchingEngineService;
import com.tradingengine.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final MatchingEngineService matchingEngineService;

    @Override
    @Transactional
    public OrderResponse placeBuyOrder(
            BuyOrderRequest request) {

        return createOrder(
                request.getUserId(),
                request.getStockSymbol(),
                request.getPrice(),
                request.getQuantity(),
                OrderType.BUY
        );
    }

    @Override
    @Transactional
    public OrderResponse placeSellOrder(
            SellOrderRequest request) {

        return createOrder(
                request.getUserId(),
                request.getStockSymbol(),
                request.getPrice(),
                request.getQuantity(),
                OrderType.SELL
        );
    }

    private OrderResponse createOrder(
            Long userId,
            String symbol,
            java.math.BigDecimal price,
            Integer quantity,
            OrderType type) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stock not found"));

        Order order = Order.builder()
                .user(user)
                .stock(stock)
                .price(price)
                .quantity(quantity)
                .remainingQuantity(quantity)
                .orderType(type)
                .status(OrderStatus.OPEN)
                .build();

        Order savedOrder =
                orderRepository.save(order);

        matchingEngineService.processOrder(savedOrder);

        return mapToResponse(savedOrder);
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"));

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"));

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getOrdersByUser(
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        return orderRepository
                .findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OrderResponse mapToResponse(Order order) {

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .stockSymbol(order.getStock().getSymbol())
                .orderType(order.getOrderType().name())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .remainingQuantity(
                        order.getRemainingQuantity())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .build();
    }
}