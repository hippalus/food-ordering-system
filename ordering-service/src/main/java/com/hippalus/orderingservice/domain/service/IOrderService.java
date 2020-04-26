package com.hippalus.orderingservice.domain.service;

import com.hippalus.orderingservice.application.request.CreateOrderRequest;
import com.hippalus.orderingservice.application.response.OrderResponse;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderService {
    @Transactional
    OrderResponse createOrder(CreateOrderRequest request) throws Exception;
}
