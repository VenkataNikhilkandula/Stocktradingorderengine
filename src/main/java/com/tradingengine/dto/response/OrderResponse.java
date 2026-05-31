package com.tradingengine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;

    private Long userId;

    private String stockSymbol;

    private String orderType;

    private BigDecimal price;

    private Integer quantity;

    private Integer remainingQuantity;

    private String status;

    private LocalDateTime createdAt;
}