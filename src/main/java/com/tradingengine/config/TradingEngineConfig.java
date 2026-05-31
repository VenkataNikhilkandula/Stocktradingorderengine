package com.tradingengine.config;

import com.tradingengine.entity.Order;
import com.tradingengine.engine.OrderBook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class TradingEngineConfig {

    @Bean
    public Map<String, OrderBook> orderBooks() {

        return new ConcurrentHashMap<>();
    }

    @Bean
    public Map<String, ReentrantLock> stockLocks() {

        return new ConcurrentHashMap<>();
    }

    @Bean
    public Comparator<Order> buyOrderComparator() {

        return (o1, o2) -> {

            int priceCompare =
                    o2.getPrice()
                            .compareTo(o1.getPrice());

            if (priceCompare == 0) {

                return o1.getCreatedAt()
                        .compareTo(o2.getCreatedAt());
            }

            return priceCompare;
        };
    }

    @Bean
    public Comparator<Order> sellOrderComparator() {

        return (o1, o2) -> {

            int priceCompare =
                    o1.getPrice()
                            .compareTo(o2.getPrice());

            if (priceCompare == 0) {

                return o1.getCreatedAt()
                        .compareTo(o2.getCreatedAt());
            }

            return priceCompare;
        };
    }
}