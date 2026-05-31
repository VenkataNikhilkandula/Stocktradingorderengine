package com.tradingengine.service.impl;

import com.tradingengine.dto.response.TradeResponse;
import com.tradingengine.entity.Trade;
import com.tradingengine.entity.User;
import com.tradingengine.exception.ResourceNotFoundException;
import com.tradingengine.repository.StockRepository;
import com.tradingengine.repository.TradeRepository;
import com.tradingengine.repository.UserRepository;
import com.tradingengine.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;

    @Override
    public List<TradeResponse> getTradesByStock(
            String symbol) {

        return tradeRepository
                .findTradesByStockSymbol(symbol)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TradeResponse> getTradesByUser(
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        return tradeRepository.findTradesByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TradeResponse> getAllTrades() {

        return tradeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TradeResponse mapToResponse(Trade trade) {

        return TradeResponse.builder()
                .tradeId(trade.getId())
                .buyOrderId(
                        trade.getBuyOrder().getId())
                .sellOrderId(
                        trade.getSellOrder().getId())
                .stockSymbol(
                        trade.getStock().getSymbol())
                .price(trade.getPrice())
                .quantity(trade.getQuantity())
                .executedAt(trade.getExecutedAt())
                .build();
    }
}