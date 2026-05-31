package com.tradingengine.service.impl;

import com.tradingengine.dto.response.OrderBookResponse;
import com.tradingengine.entity.Order;
import com.tradingengine.entity.Stock;
import com.tradingengine.enums.OrderType;
import com.tradingengine.exception.ResourceNotFoundException;
import com.tradingengine.repository.OrderRepository;
import com.tradingengine.repository.StockRepository;
import com.tradingengine.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderBookServiceImpl
        implements OrderBookService {

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderBookResponse getOrderBook(
            String symbol) {

        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stock not found"));

        List<Order> buyOrders =
                orderRepository.findBuyOrders(
                        stock,
                        OrderType.BUY
                );

        List<Order> sellOrders =
                orderRepository.findSellOrders(
                        stock,
                        OrderType.SELL
                );

        return OrderBookResponse.builder()
                .stockSymbol(symbol)
                .buyOrders(
                        buyOrders.stream()
                                .map(this::mapEntry)
                                .toList()
                )
                .sellOrders(
                        sellOrders.stream()
                                .map(this::mapEntry)
                                .toList()
                )
                .build();
    }

    @Override
    public OrderBookResponse getMarketDepth(
            String symbol) {

        return getOrderBook(symbol);
    }

    private OrderBookResponse.OrderBookEntry
    mapEntry(Order order) {

        return OrderBookResponse.OrderBookEntry
                .builder()
                .orderId(order.getId())
                .orderType(order.getOrderType().name())
                .quantity(order.getQuantity())
                .remainingQuantity(
                        order.getRemainingQuantity())
                .price(order.getPrice().toString())
                .status(order.getStatus().name())
                .build();
    }
}