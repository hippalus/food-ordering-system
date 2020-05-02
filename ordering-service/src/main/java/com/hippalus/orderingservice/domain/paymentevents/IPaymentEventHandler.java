package com.hippalus.orderingservice.domain.paymentevents;

import com.hippalus.orderingservice.domain.models.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

public interface IPaymentEventHandler {

    @Transactional
    void handleEvent(String paymentEvent);

    @Transactional(isolation = READ_COMMITTED)
    Optional<Orders> getOrderById(PaymentEvent payment);
}
