package com.tradingengine.service.impl;

import com.tradingengine.dto.request.StockRequest;
import com.tradingengine.dto.response.StockResponse;
import com.tradingengine.entity.Stock;
import com.tradingengine.exception.ResourceNotFoundException;
import com.tradingengine.repository.StockRepository;
import com.tradingengine.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public StockResponse createStock(StockRequest request) {

        Stock stock = Stock.builder()
                .symbol(request.getSymbol())
                .companyName(request.getCompanyName())
                .build();

        return mapToResponse(
                stockRepository.save(stock)
        );
    }

    @Override
    public StockResponse getStockBySymbol(String symbol) {

        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stock not found"));

        return mapToResponse(stock);
    }

    @Override
    public List<StockResponse> getAllStocks() {

        return stockRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private StockResponse mapToResponse(Stock stock) {

        return StockResponse.builder()
                .id(stock.getId())
                .symbol(stock.getSymbol())
                .companyName(stock.getCompanyName())
                .build();
    }
}