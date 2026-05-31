package com.tradingengine.util;

public final class Constants {

    private Constants() {
    }

    
    public static final String API_BASE =
            "/api";

    
    public static final String ORDER_OPEN =
            "OPEN";

    public static final String ORDER_PARTIAL =
            "PARTIAL";

    public static final String ORDER_FILLED =
            "FILLED";

    public static final String ORDER_CANCELLED =
            "CANCELLED";

   
    public static final String BUY =
            "BUY";

    public static final String SELL =
            "SELL";

    
    public static final String ROLE_ADMIN =
            "ROLE_ADMIN";

    public static final String ROLE_TRADER =
            "ROLE_TRADER";

    
    public static final int MIN_QUANTITY = 1;

    public static final double MIN_PRICE = 0.01;

   
    public static final int MAX_ORDER_BOOK_SIZE =
            100000;

    public static final String ORDER_BOOK_CACHE =
            "ORDER_BOOK_CACHE";

    
    public static final String USER_NOT_FOUND =
            "User not found";

    public static final String STOCK_NOT_FOUND =
            "Stock not found";

    public static final String ORDER_NOT_FOUND =
            "Order not found";

    public static final String INVALID_ORDER =
            "Invalid order";

    public static final String INSUFFICIENT_BALANCE =
            "Insufficient balance";

    
    public static final String ORDER_PLACED =
            "Order placed successfully";

    public static final String ORDER_CANCELLED_MSG =
            "Order cancelled successfully";

    public static final String TRADE_EXECUTED =
            "Trade executed successfully";
}