package com.mylearning.domain.ports.output.repository;

import com.mylearning.domain.entity.Order;
import com.mylearning.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId);

}
