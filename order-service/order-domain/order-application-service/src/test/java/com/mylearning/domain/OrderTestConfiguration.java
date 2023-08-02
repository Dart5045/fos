package com.mylearning.domain;


import com.mylearning.domain.ports.output.mesage.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.mylearning.domain.ports.output.mesage.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.mylearning.domain.ports.output.mesage.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.mylearning.domain.ports.output.repository.CustomerRepository;
import com.mylearning.domain.ports.output.repository.OrderRepository;
import com.mylearning.domain.ports.output.repository.RestaurantRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.mylearning")
public class OrderTestConfiguration {

    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher(){
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository(){
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository(){
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository(){
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }
}
