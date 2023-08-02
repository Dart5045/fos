package com.mylearning.domain;

import com.mylearning.domain.dto.create.CreateOrderCommand;
import com.mylearning.domain.dto.create.CreateOrderResponse;
import com.mylearning.domain.event.OrderCreatedEvent;
import com.mylearning.domain.mapper.OrderDataMapper;
import com.mylearning.domain.ports.output.mesage.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final OrderCreateHelper  orderCreateHelper;
    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper,
                                     OrderDataMapper orderDataMapper,
                                     OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }


    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand){
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.createPersistence(createOrderCommand);
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        log.info("Order is created with id:{}",orderCreatedEvent.getOrder().getId().getT());
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(),"Order created Successfully");

    }
}
