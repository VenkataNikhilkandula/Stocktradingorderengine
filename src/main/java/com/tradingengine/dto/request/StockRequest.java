package com.tradingengine.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {

    @NotBlank(message = "Stock symbol is required")
    private String symbol;

    @NotBlank(message = "Company name is required")
    private String companyName;
}