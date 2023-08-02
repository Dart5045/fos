package com.mylearning.domain.event;

import com.mylearning.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createdAd) {
        super(order, createdAd);
    }
}