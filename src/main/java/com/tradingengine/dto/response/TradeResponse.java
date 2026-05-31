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
public class TradeResponse {

    private Long tradeId;

    private Long buyOrderId;

    private Long sellOrderId;

    private String stockSymbol;

    private BigDecimal price;

    private Integer quantity;

    private LocalDateTime executedAt;
}