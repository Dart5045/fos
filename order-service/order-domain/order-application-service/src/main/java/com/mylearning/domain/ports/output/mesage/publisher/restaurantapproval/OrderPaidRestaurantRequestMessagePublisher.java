package com.mylearning.domain.ports.output.mesage.publisher.restaurantapproval;

import com.mylearning.domain.event.OrderCreatedEvent;
import com.mylearning.domain.event.OrderPaidEvent;
import com.mylearning.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {


}
