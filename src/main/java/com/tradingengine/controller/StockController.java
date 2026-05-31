package com.tradingengine.controller;

import com.tradingengine.dto.request.StockRequest;
import com.tradingengine.dto.response.ApiResponse;
import com.tradingengine.dto.response.StockResponse;
import com.tradingengine.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<ApiResponse<StockResponse>> createStock(
            @Valid @RequestBody StockRequest request) {

        StockResponse response =
                stockService.createStock(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Stock created successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllStocks() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Stocks fetched successfully",
                        stockService.getAllStocks()
                )
        );
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<ApiResponse<StockResponse>> getStockBySymbol(
            @PathVariable String symbol) {

        StockResponse response =
                stockService.getStockBySymbol(symbol);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Stock fetched successfully",
                        response
                )
        );
    }
}