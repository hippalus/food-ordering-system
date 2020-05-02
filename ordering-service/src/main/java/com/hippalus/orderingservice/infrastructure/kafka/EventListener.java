package com.hippalus.orderingservice.infrastructure.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventListener {

    String PAYMENT_TOPIC = "payments-topic";

    @Input(PAYMENT_TOPIC)
    SubscribableChannel paymentEventsIn();
}