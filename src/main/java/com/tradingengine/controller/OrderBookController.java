package com.tradingengine.controller;

import com.tradingengine.dto.response.ApiResponse;
import com.tradingengine.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderbook")
@RequiredArgsConstructor
public class OrderBookController {

    private final OrderBookService orderBookService;

    @GetMapping("/{symbol}")
    public ResponseEntity<ApiResponse<?>> getOrderBook(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Order book fetched successfully",
                        orderBookService.getOrderBook(symbol)
                )
        );
    }

    @GetMapping("/{symbol}/depth")
    public ResponseEntity<ApiResponse<?>> getMarketDepth(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Market depth fetched successfully",
                        orderBookService.getMarketDepth(symbol)
                )
        );
    }
}