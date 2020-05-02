package com.hippalus.orderingservice.infrastructure.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSender {
    String ORDER_EVENT_OUTBOUND = "order-event-outbound";
    String ORDERS_TOPIC = "orders-topic";

    @Output(ORDER_EVENT_OUTBOUND)
    MessageChannel orderEventOutbound();
}
