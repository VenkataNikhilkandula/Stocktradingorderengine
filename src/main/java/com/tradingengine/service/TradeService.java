package com.tradingengine.service;

import com.tradingengine.dto.response.TradeResponse;

import java.util.List;

public interface TradeService {

    List<TradeResponse> getTradesByStock(String symbol);

    List<TradeResponse> getTradesByUser(Long userId);

    List<TradeResponse> getAllTrades();
}