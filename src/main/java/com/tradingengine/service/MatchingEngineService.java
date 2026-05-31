package com.tradingengine.service;

import com.tradingengine.entity.Order;

public interface MatchingEngineService {

    void processOrder(Order order);
}