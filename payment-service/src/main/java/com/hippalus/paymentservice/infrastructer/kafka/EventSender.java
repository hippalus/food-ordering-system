package com.hippalus.paymentservice.infrastructer.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSender {
    String PAYMENT_EVENT_OUTBOUND = "payment-event-outbound";
    String PAYMENTS_TOPIC="payments-topic";

    @Output(PAYMENT_EVENT_OUTBOUND)
    MessageChannel paymentEventOutbound();
}
