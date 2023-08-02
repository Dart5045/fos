package com.mylearning.domain.event;


import com.mylearning.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent{
    public OrderCreatedEvent(Order order, ZonedDateTime createdAd) {
        super(order, createdAd);
    }
}
