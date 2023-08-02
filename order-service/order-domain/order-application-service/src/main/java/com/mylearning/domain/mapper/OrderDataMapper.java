package com.mylearning.domain.mapper;


import com.mylearning.domain.dto.create.CreateOrderCommand;
import com.mylearning.domain.dto.create.CreateOrderResponse;
import com.mylearning.domain.dto.create.OrderAddress;
import com.mylearning.domain.dto.track.TrackOrderResponse;
import com.mylearning.domain.entity.Order;
import com.mylearning.domain.entity.OrderItem;
import com.mylearning.domain.entity.Product;
import com.mylearning.domain.entity.Restaurant;
import com.mylearning.domain.valueobject.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand){
        return Restaurant.builder()
                .restaurantId( new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand
                        .getItems()
                        .stream()
                        .map(orderItem ->
                            new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList())
                )
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand){
        return Order
                .builder()
                .orderId(new OrderId(createOrderCommand.getCustomerId()))
                .restaurantId( new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getOrderAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntity(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntity(List<com.mylearning.domain.dto.create.OrderItem> items) {
    return items.stream()
            .map(
                orderItem -> OrderItem
                        .builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()
                )
            .collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order){
        return TrackOrderResponse
                .builder()
                .orderTrackingId(order.getTrackingId().getT())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order,String message){
         return CreateOrderResponse
                .builder()
                 .orderTackingId(order.getTrackingId().getT())
                 .message(message)
                .build();
    }

}
