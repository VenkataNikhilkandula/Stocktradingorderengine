package com.tradingengine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookResponse {

    private String stockSymbol;

    private List<OrderBookEntry> buyOrders;

    private List<OrderBookEntry> sellOrders;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderBookEntry {

        private Long orderId;

        private String orderType;

        private Integer quantity;

        private Integer remainingQuantity;

        private String price;

        private String status;
    }
}