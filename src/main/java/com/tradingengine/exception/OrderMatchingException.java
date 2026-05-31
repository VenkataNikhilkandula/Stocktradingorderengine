package com.tradingengine.exception;

public class OrderMatchingException
        extends RuntimeException {

    public OrderMatchingException(String message) {
        super(message);
    }
}