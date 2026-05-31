package com.tradingengine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "trades",
        indexes = {

                @Index(name = "idx_trade_buy_order",
                        columnList = "buy_order_id"),

                @Index(name = "idx_trade_sell_order",
                        columnList = "sell_order_id"),

                @Index(name = "idx_trade_executed",
                        columnList = "executedAt")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_order_id",
            nullable = false)
    private Order buyOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_order_id",
            nullable = false)
    private Order sellOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id",
            nullable = false)
    private Stock stock;

    @Column(nullable = false,
            precision = 19,
            scale = 4)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false,
            updatable = false)
    private LocalDateTime executedAt;

    @PrePersist
    public void prePersist() {
        executedAt = LocalDateTime.now();
    }
}