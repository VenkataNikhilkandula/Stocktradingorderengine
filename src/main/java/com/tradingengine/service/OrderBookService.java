package com.tradingengine.service;

import com.tradingengine.dto.response.OrderBookResponse;

public interface OrderBookService {

    OrderBookResponse getOrderBook(String symbol);

    OrderBookResponse getMarketDepth(String symbol);
}