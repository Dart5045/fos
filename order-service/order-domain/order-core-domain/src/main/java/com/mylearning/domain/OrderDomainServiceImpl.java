package com.mylearning.domain;

import com.mylearning.domain.entity.Order;
import com.mylearning.domain.entity.Product;
import com.mylearning.domain.entity.Restaurant;
import com.mylearning.domain.event.OrderCancelledEvent;
import com.mylearning.domain.event.OrderCreatedEvent;
import com.mylearning.domain.event.OrderPaidEvent;
import com.mylearning.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements   OrderDomainService{
    private static final String ZONEID = "Europe/Madrid";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order,restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated",order.getId());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(ZONEID)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id : {} is paid",order.getId());
        return new OrderPaidEvent(order,ZonedDateTime.now(ZoneId.of(ZONEID)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id : {} is approved",order.getId());

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id : {}",order.getId());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(ZONEID)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id : {} is cancelled",order.getId());

    }

    private void validateRestaurant(Restaurant restaurant) {
        if(!restaurant.isActive()){
            throw new OrderDomainException("Restaurant with id "+restaurant.getId()+" is currently not active");
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(
                orderItem -> restaurant.getProducts().forEach(
                        restaurantProduct -> {
                            Product currentProduct = orderItem.getProduct();
                            if(currentProduct.equals(restaurantProduct)){
                                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
                            }
                        }
                )
        );

    }
}
