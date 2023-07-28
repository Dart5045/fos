package com.mylearning.domain;


import com.mylearning.domain.dto.create.CreateOrderCommand;
import com.mylearning.domain.entity.Customer;
import com.mylearning.domain.entity.Order;
import com.mylearning.domain.entity.Restaurant;
import com.mylearning.domain.event.OrderCreatedEvent;
import com.mylearning.domain.exception.OrderDomainException;
import com.mylearning.domain.mapper.OrderDataMapper;
import com.mylearning.domain.ports.output.repository.CustomerRepository;
import com.mylearning.domain.ports.output.repository.OrderRepository;
import com.mylearning.domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             RestaurantRepository restaurantRepository,
                             OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent createPersistence(CreateOrderCommand createOrderCommand){
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);

        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        order.initializeOrder();
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is created with id: {}",orderCreatedEvent.getOrder().getId().getT());
        return orderCreatedEvent;
    }


    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> restaurantInformation = restaurantRepository.findRestaurantInformation(restaurant);
        if(restaurantInformation.isEmpty()){
            log.warn("Could not find restaurant with restaurant id:{} ",createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Could not find restaurant with restaurant id:"+createOrderCommand.getRestaurantId());

        }

        return restaurantInformation.get();
    }


    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if(customer.isEmpty()){
            log.warn("Could not find customer with customer id:{} ",customerId);
            throw new OrderDomainException("Could not find customer with customer id: "+customerId);
        }
    }

    private Order saveOrder(Order order){
        Order save = orderRepository.save(order);
        if(order == null){
            log.info("Could not save order");
            throw new OrderDomainException("Could not save order");
        }
        log.info("Order is saved with id:{}",order.getId());
        return order;
    }
}