package com.tradingengine.engine;

import com.tradingengine.entity.Order;
import com.tradingengine.entity.Trade;
import com.tradingengine.enums.OrderStatus;
import com.tradingengine.enums.OrderType;
import com.tradingengine.repository.OrderRepository;
import com.tradingengine.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TradeExecutor {

    private final TradeRepository tradeRepository;
    private final OrderRepository orderRepository;

   
    public void execute(
            Order buyOrder,
            Order sellOrder,
            int quantity) {

        
        buyOrder.setRemainingQuantity(
                buyOrder.getRemainingQuantity()
                        - quantity
        );

        sellOrder.setRemainingQuantity(
                sellOrder.getRemainingQuantity()
                        - quantity
        );

        
        updateStatus(buyOrder);
        updateStatus(sellOrder);

        
        Trade trade = Trade.builder()
                .buyOrder(buyOrder)
                .sellOrder(sellOrder)
                .stock(buyOrder.getStock())
                .price(sellOrder.getPrice())
                .quantity(quantity)
                .build();

        tradeRepository.save(trade);

        orderRepository.save(buyOrder);
        orderRepository.save(sellOrder);
    }

    
    private void updateStatus(Order order) {

        if (order.getRemainingQuantity() == 0) {

            order.setStatus(OrderStatus.FILLED);

        } else if (
                order.getRemainingQuantity()
                        < order.getQuantity()
        ) {

            order.setStatus(OrderStatus.PARTIAL);

        } else {

            order.setStatus(OrderStatus.OPEN);
        }
    }
}