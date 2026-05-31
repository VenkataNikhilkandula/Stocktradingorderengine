package com.tradingengine.engine;

import com.tradingengine.entity.Order;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public class PriceLevel {

    
    private final BigDecimal price;

    
    private final Queue<Order> orders;

    public PriceLevel(BigDecimal price) {

        this.price = price;
        this.orders = new LinkedList<>();
    }

    
    public void addOrder(Order order) {

        orders.offer(order);
    }

    
    public Order pollOrder() {

        return orders.poll();
    }

    
    public Order peekOrder() {

        return orders.peek();
    }

    
    public boolean isEmpty() {

        return orders.isEmpty();
    }

    
    public int getTotalQuantity() {

        return orders.stream()
                .mapToInt(Order::getRemainingQuantity)
                .sum();
    }
}