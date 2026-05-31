package com.tradingengine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "stocks",
        indexes = {
                @Index(name = "idx_stock_symbol",
                        columnList = "symbol")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            unique = true)
    private String symbol;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false,
            updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "stock",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}