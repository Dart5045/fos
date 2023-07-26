package com.mylearning.domain.event;

import com.mylearning.domain.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<Order>{
    private final Order order;
    private final ZonedDateTime createdAd;

    public OrderEvent(Order order, ZonedDateTime createdAd) {
        this.order = order;
        this.createdAd = createdAd;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAd() {
        return createdAd;
    }
}
