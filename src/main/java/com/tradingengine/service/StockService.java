package com.tradingengine.service;

import com.tradingengine.dto.request.StockRequest;
import com.tradingengine.dto.response.StockResponse;

import java.util.List;

public interface StockService {

    StockResponse createStock(StockRequest request);

    StockResponse getStockBySymbol(String symbol);

    List<StockResponse> getAllStocks();
}