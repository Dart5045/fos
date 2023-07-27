package com.mylearning.domain.ports.input.service;

import com.mylearning.domain.dto.create.CreateOrderCommand;
import com.mylearning.domain.dto.create.CreateOrderResponse;
import com.mylearning.domain.dto.track.TrackOrderQuery;
import com.mylearning.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
