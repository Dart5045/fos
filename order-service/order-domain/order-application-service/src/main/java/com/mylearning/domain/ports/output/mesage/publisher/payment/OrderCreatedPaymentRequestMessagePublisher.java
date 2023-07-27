package com.mylearning.domain.ports.output.mesage.publisher.payment;

import com.mylearning.domain.event.DomainEvent;
import com.mylearning.domain.event.OrderCreatedEvent;
import com.mylearning.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {


}
