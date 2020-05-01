package com.hippalus.paymentservice.application.service;

import com.hippalus.paymentservice.domain.domainevents.IDomainEventPublisher;
import com.hippalus.paymentservice.infrastructer.kafka.EventSender;
import com.hippalus.sharedkernel.domain.DomainEvent;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hippalus.paymentservice.infrastructer.kafka.EventSender.*;


@Service
@EnableBinding(EventSender.class)
@RequiredArgsConstructor
public class PaymentEventPublisher implements IDomainEventPublisher {

    private final EventSender eventSender;

    @Override
    @SendTo(value = PAYMENTS_TOPIC)
    public void publishEvent(List<DomainEvent<? extends DomainObjectId>> eventList) {
        eventList.forEach(event -> eventSender.paymentEventOutbound()
                .send(MessageBuilder.
                        withPayload(event)
                        .build()));
    }
}
