package com.mylearning;

import com.mylearning.domain.dto.track.TrackOrderQuery;
import com.mylearning.domain.dto.track.TrackOrderResponse;
import com.mylearning.domain.entity.Order;
import com.mylearning.domain.exception.OrderDomainException;
import com.mylearning.domain.exception.OrderNotFoundException;
import com.mylearning.domain.mapper.OrderDataMapper;
import com.mylearning.domain.ports.output.repository.OrderRepository;
import com.mylearning.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper,
                                    OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery){
        Optional<Order> orderResult = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if(orderResult.isEmpty())
        {
            log.warn("Could not find order with tracking id:{}",trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id:"+trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
