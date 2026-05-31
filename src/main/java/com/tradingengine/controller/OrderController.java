package com.tradingengine.controller;

import com.tradingengine.dto.request.BuyOrderRequest;
import com.tradingengine.dto.request.SellOrderRequest;
import com.tradingengine.dto.response.ApiResponse;
import com.tradingengine.dto.response.OrderResponse;
import com.tradingengine.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/buy")
    public ResponseEntity<ApiResponse<OrderResponse>> placeBuyOrder(
            @Valid @RequestBody BuyOrderRequest request) {

        OrderResponse response =
                orderService.placeBuyOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Buy order placed successfully",
                        response
                ));
    }

    @PostMapping("/sell")
    public ResponseEntity<ApiResponse<OrderResponse>> placeSellOrder(
            @Valid @RequestBody SellOrderRequest request) {

        OrderResponse response =
                orderService.placeSellOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Sell order placed successfully",
                        response
                ));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<String>> cancelOrder(
            @PathVariable Long orderId) {

        orderService.cancelOrder(orderId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Order cancelled successfully",
                        "CANCELLED"
                )
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(
            @PathVariable Long orderId) {

        OrderResponse response =
                orderService.getOrderById(orderId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Order fetched successfully",
                        response
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<?>> getOrdersByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "User orders fetched successfully",
                        orderService.getOrdersByUser(userId)
                )
        );
    }
}