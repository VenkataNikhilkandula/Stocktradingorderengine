package com.tradingengine.repository;

import com.tradingengine.entity.Stock;
import com.tradingengine.entity.Trade;
import com.tradingengine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TradeRepository
        extends JpaRepository<Trade, Long> {

    
    List<Trade> findTop20ByStockOrderByExecutedAtDesc(
            Stock stock
    );

    
    @Query("""
            SELECT t
            FROM Trade t
            WHERE t.buyOrder.user = :user
               OR t.sellOrder.user = :user
            ORDER BY t.executedAt DESC
            """)
    List<Trade> findTradesByUser(
            @Param("user") User user
    );

    
    @Query("""
            SELECT t
            FROM Trade t
            WHERE t.stock.symbol = :symbol
            ORDER BY t.executedAt DESC
            """)
    List<Trade> findTradesByStockSymbol(
            @Param("symbol") String symbol
    );

    
    @Query("""
            SELECT COALESCE(SUM(t.quantity), 0)
            FROM Trade t
            WHERE t.stock.symbol = :symbol
            """)
    Long getTotalVolume(
            @Param("symbol") String symbol
    );

    
    Trade findTopByStockOrderByExecutedAtDesc(
            Stock stock
    );
}