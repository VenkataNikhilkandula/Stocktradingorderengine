package com.tradingengine.engine;

import com.tradingengine.entity.Order;
import com.tradingengine.enums.OrderType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Getter
public class OrderBook {

   
    private final TreeMap<BigDecimal, PriceLevel>
            buyBook;

    
    private final TreeMap<BigDecimal, PriceLevel>
            sellBook;

    public OrderBook() {

        
        buyBook = new TreeMap<>(
                Comparator.reverseOrder()
        );

        
        sellBook = new TreeMap<>();
    }

    
    public void addOrder(Order order) {

        Map<BigDecimal, PriceLevel> book =
                order.getOrderType() == OrderType.BUY
                        ? buyBook
                        : sellBook;

        PriceLevel level =
                book.computeIfAbsent(
                        order.getPrice(),
                        PriceLevel::new
                );

        level.addOrder(order);
    }

    
    public Order getBestBuyOrder() {

        if (buyBook.isEmpty()) {
            return null;
        }

        return buyBook.firstEntry()
                .getValue()
                .peekOrder();
    }

    public Order getBestSellOrder() {

        if (sellBook.isEmpty()) {
            return null;
        }

        return sellBook.firstEntry()
                .getValue()
                .peekOrder();
    }

    
    public void cleanup() {

        buyBook.entrySet()
                .removeIf(
                        entry ->
                                entry.getValue().isEmpty()
                );

        sellBook.entrySet()
                .removeIf(
                        entry ->
                                entry.getValue().isEmpty()
                );
    }
}