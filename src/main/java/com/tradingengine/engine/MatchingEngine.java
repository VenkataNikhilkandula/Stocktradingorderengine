package com.tradingengine.engine;

import com.tradingengine.entity.Order;
import com.tradingengine.enums.OrderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MatchingEngine {

    private final TradeExecutor tradeExecutor;

    
    public void match(
            OrderBook orderBook,
            Order incomingOrder) {

       
        orderBook.addOrder(incomingOrder);

        while (true) {

            Order bestBuy =
                    orderBook.getBestBuyOrder();

            Order bestSell =
                    orderBook.getBestSellOrder();

            if (bestBuy == null
                    || bestSell == null) {
                break;
            }

            
            if (
                    bestBuy.getPrice()
                            .compareTo(
                                    bestSell.getPrice()
                            ) < 0
            ) {
                break;
            }

            
            int quantity =
                    Math.min(
                            bestBuy.getRemainingQuantity(),
                            bestSell.getRemainingQuantity()
                    );

            tradeExecutor.execute(
                    bestBuy,
                    bestSell,
                    quantity
            );

            log.info(
                    "Trade executed BUY={} SELL={} QTY={}",
                    bestBuy.getId(),
                    bestSell.getId(),
                    quantity
            );

            
            if (bestBuy.getRemainingQuantity()
                    == 0) {

                orderBook.getBuyBook()
                        .firstEntry()
                        .getValue()
                        .pollOrder();
            }

            
            if (bestSell.getRemainingQuantity()
                    == 0) {

                orderBook.getSellBook()
                        .firstEntry()
                        .getValue()
                        .pollOrder();
            }

            
            orderBook.cleanup();
        }
    }
}