package com.tradingengine.service.impl;

import com.tradingengine.entity.Order;
import com.tradingengine.entity.Trade;
import com.tradingengine.enums.OrderStatus;
import com.tradingengine.enums.OrderType;
import com.tradingengine.repository.OrderRepository;
import com.tradingengine.repository.TradeRepository;
import com.tradingengine.service.MatchingEngineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingEngineServiceImpl
        implements MatchingEngineService {

    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;

    @Override
    @Transactional
    public void processOrder(Order incomingOrder) {

        List<Order> matchingOrders;

        if (incomingOrder.getOrderType()
                == OrderType.BUY) {

            matchingOrders =
                    orderRepository.findSellOrders(
                            incomingOrder.getStock(),
                            OrderType.SELL
                    );

        } else {

            matchingOrders =
                    orderRepository.findBuyOrders(
                            incomingOrder.getStock(),
                            OrderType.BUY
                    );
        }

        for (Order existingOrder : matchingOrders) {

            if (incomingOrder.getRemainingQuantity()
                    <= 0) {
                break;
            }

            boolean match =
                    incomingOrder.getOrderType()
                            == OrderType.BUY
                            ? incomingOrder.getPrice()
                            .compareTo(
                                    existingOrder.getPrice())
                            >= 0
                            : incomingOrder.getPrice()
                            .compareTo(
                                    existingOrder.getPrice())
                            <= 0;

            if (!match) {
                break;
            }

            int matchedQuantity =
                    Math.min(
                            incomingOrder
                                    .getRemainingQuantity(),
                            existingOrder
                                    .getRemainingQuantity()
                    );

            executeTrade(
                    incomingOrder,
                    existingOrder,
                    matchedQuantity
            );
        }

        orderRepository.save(incomingOrder);
    }

    private void executeTrade(
            Order incomingOrder,
            Order existingOrder,
            int matchedQuantity) {

        incomingOrder.setRemainingQuantity(
                incomingOrder.getRemainingQuantity()
                        - matchedQuantity
        );

        existingOrder.setRemainingQuantity(
                existingOrder.getRemainingQuantity()
                        - matchedQuantity
        );

        updateOrderStatus(incomingOrder);
        updateOrderStatus(existingOrder);

        Trade trade = Trade.builder()
                .buyOrder(
                        incomingOrder.getOrderType()
                                == OrderType.BUY
                                ? incomingOrder
                                : existingOrder
                )
                .sellOrder(
                        incomingOrder.getOrderType()
                                == OrderType.SELL
                                ? incomingOrder
                                : existingOrder
                )
                .stock(incomingOrder.getStock())
                .price(existingOrder.getPrice())
                .quantity(matchedQuantity)
                .build();

        tradeRepository.save(trade);

        orderRepository.save(existingOrder);
        orderRepository.save(incomingOrder);
    }

    private void updateOrderStatus(Order order) {

        if (order.getRemainingQuantity() == 0) {

            order.setStatus(OrderStatus.FILLED);

        } else if (order.getRemainingQuantity()
                < order.getQuantity()) {

            order.setStatus(OrderStatus.PARTIAL);

        } else {

            order.setStatus(OrderStatus.OPEN);
        }
    }
}