package com.tradingengine.util;

import com.tradingengine.dto.response.OrderBookResponse;
import com.tradingengine.dto.response.OrderResponse;
import com.tradingengine.dto.response.StockResponse;
import com.tradingengine.dto.response.TradeResponse;
import com.tradingengine.dto.response.UserResponse;
import com.tradingengine.entity.Order;
import com.tradingengine.entity.Stock;
import com.tradingengine.entity.Trade;
import com.tradingengine.entity.User;

public final class Mapper {

    private Mapper() {
    }

    
    public static UserResponse mapToUserResponse(
            User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .balance(user.getBalance())
                .createdAt(user.getCreatedAt())
                .build();
    }

   
    public static StockResponse mapToStockResponse(
            Stock stock) {

        return StockResponse.builder()
                .id(stock.getId())
                .symbol(stock.getSymbol())
                .companyName(stock.getCompanyName())
                .build();
    }

    public static OrderResponse mapToOrderResponse(
            Order order) {

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .stockSymbol(
                        order.getStock().getSymbol()
                )
                .orderType(
                        order.getOrderType().name()
                )
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .remainingQuantity(
                        order.getRemainingQuantity()
                )
                .status(
                        order.getStatus().name()
                )
                .createdAt(order.getCreatedAt())
                .build();
    }

    
    public static TradeResponse mapToTradeResponse(
            Trade trade) {

        return TradeResponse.builder()
                .tradeId(trade.getId())
                .buyOrderId(
                        trade.getBuyOrder().getId()
                )
                .sellOrderId(
                        trade.getSellOrder().getId()
                )
                .stockSymbol(
                        trade.getStock().getSymbol()
                )
                .price(trade.getPrice())
                .quantity(trade.getQuantity())
                .executedAt(trade.getExecutedAt())
                .build();
    }

    
    public static OrderBookResponse.OrderBookEntry
    mapToOrderBookEntry(Order order) {

        return OrderBookResponse.OrderBookEntry
                .builder()
                .orderId(order.getId())
                .orderType(
                        order.getOrderType().name()
                )
                .quantity(order.getQuantity())
                .remainingQuantity(
                        order.getRemainingQuantity()
                )
                .price(order.getPrice().toString())
                .status(
                        order.getStatus().name()
                )
                .build();
    }
}