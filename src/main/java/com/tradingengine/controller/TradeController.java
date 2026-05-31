package com.tradingengine.controller;

import com.tradingengine.dto.response.ApiResponse;
import com.tradingengine.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/stock/{symbol}")
    public ResponseEntity<ApiResponse<?>> getTradesByStock(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Trades fetched successfully",
                        tradeService.getTradesByStock(symbol)
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<?>> getTradesByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "User trades fetched successfully",
                        tradeService.getTradesByUser(userId)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllTrades() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Trades fetched successfully",
                        tradeService.getAllTrades()
                )
        );
    }
}