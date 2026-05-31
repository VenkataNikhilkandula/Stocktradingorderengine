package com.tradingengine.util;

import com.tradingengine.dto.request.BuyOrderRequest;
import com.tradingengine.dto.request.SellOrderRequest;
import com.tradingengine.entity.Order;
import com.tradingengine.enums.OrderStatus;
import com.tradingengine.exception.InvalidOrderException;

import java.math.BigDecimal;

public final class Validation {

    private Validation() {
    }

    
    public static void validateBuyOrder(
            BuyOrderRequest request) {

        validatePrice(request.getPrice());

        validateQuantity(request.getQuantity());
    }

    
    public static void validateSellOrder(
            SellOrderRequest request) {

        validatePrice(request.getPrice());

        validateQuantity(request.getQuantity());
    }

   
    public static void validatePrice(
            BigDecimal price) {

        if (price == null
                || price.compareTo(
                BigDecimal.ZERO) <= 0) {

            throw new InvalidOrderException(
                    "Price must be greater than zero"
            );
        }
    }

    
    public static void validateQuantity(
            Integer quantity) {

        if (quantity == null
                || quantity <= 0) {

            throw new InvalidOrderException(
                    "Quantity must be greater than zero"
            );
        }
    }

    
    public static void validateCancellation(
            Order order) {

        if (order.getStatus()
                == OrderStatus.FILLED) {

            throw new InvalidOrderException(
                    "Filled orders cannot be cancelled"
            );
        }

        if (order.getStatus()
                == OrderStatus.CANCELLED) {

            throw new InvalidOrderException(
                    "Order already cancelled"
            );
        }
    }

    
    public static void validateRemainingQuantity(
            Order order) {

        if (order.getRemainingQuantity() < 0) {

            throw new InvalidOrderException(
                    "Remaining quantity cannot be negative"
            );
        }
    }

    
    public static void validateStockSymbol(
            String symbol) {

        if (symbol == null
                || symbol.trim().isEmpty()) {

            throw new InvalidOrderException(
                    "Stock symbol is required"
            );
        }

        if (!symbol.matches("^[A-Z]{1,10}$")) {

            throw new InvalidOrderException(
                    "Invalid stock symbol format"
            );
        }
    }
}