package com.tradingengine.service;

import com.tradingengine.dto.request.BuyOrderRequest;
import com.tradingengine.dto.request.SellOrderRequest;
import com.tradingengine.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeBuyOrder(BuyOrderRequest request);

    OrderResponse placeSellOrder(SellOrderRequest request);

    void cancelOrder(Long orderId);

    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getOrdersByUser(Long userId);
}