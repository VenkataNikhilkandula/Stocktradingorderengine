package com.tradingengine.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellOrderRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Stock symbol is required")
    private String stockSymbol;

    @DecimalMin(value = "0.01",
            message = "Price must be greater than 0")
    private BigDecimal price;

    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;
}