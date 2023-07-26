package com.mylearning.domain.event;

import com.mylearning.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends  OrderEvent{
    public OrderPaidEvent(Order order, ZonedDateTime createdAd) {
        super(order, createdAd);
    }
}
