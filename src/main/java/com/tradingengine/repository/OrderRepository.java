package com.tradingengine.repository;

import com.tradingengine.entity.Order;
import com.tradingengine.entity.Stock;
import com.tradingengine.entity.User;
//import com.tradingengine.enums.OrderStatus;
//import com.tradingengine.enums.OrderType;

import org.apache.logging.log4j.internal.LogManagerStatus;
import org.hibernate.annotations.DialectOverride.OrderBys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long> {

    
    List<Order> findByUserOrderByCreatedAtDesc(User user);

    
    List<Order> findByStock(Stock stock);

   
    List<Order> findByStockAndStatus(
            Stock stock,
            OrderBys status
    );

    
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.stock = :stock
            AND o.orderType = :orderType
            AND o.status IN ('OPEN', 'PARTIAL')
            ORDER BY o.price DESC,
                     o.createdAt ASC
            """)
    List<Order> findBuyOrders(
            @Param("stock") Stock stock,
            @Param("orderType") com.tradingengine.enums.OrderType buy
    );

    
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.stock = :stock
            AND o.orderType = :orderType
            AND o.status IN ('OPEN', 'PARTIAL')
            ORDER BY o.price ASC,
                     o.createdAt ASC
            """)
    List<Order> findSellOrders(
            @Param("stock") Stock stock,
            @Param("orderType") com.tradingengine.enums.OrderType sell
    );

    
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.stock.symbol = :symbol
            AND o.status IN ('OPEN', 'PARTIAL')
            ORDER BY o.price DESC
            """)
    List<Order> getMarketDepth(
            @Param("symbol") String symbol
    );

    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT o
            FROM Order o
            WHERE o.id = :orderId
            """)
    Optional<Order> findOrderWithLock(
            @Param("orderId") Long orderId
    );

    
    long countByStatus(OrderBys status);

    
    List<Order> findByUserAndStatusIn(
            User user,
            List<LogManagerStatus> statuses
    );
}