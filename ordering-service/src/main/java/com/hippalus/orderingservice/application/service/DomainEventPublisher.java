package com.hippalus.orderingservice.application.service;

import com.hippalus.orderingservice.domain.domainevents.IDomainEventPublisher;
import com.hippalus.orderingservice.infrastructure.kafka.EventSender;
import com.hippalus.sharedkernel.domain.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hippalus.orderingservice.infrastructure.kafka.EventSender.ORDERS_TOPIC;

@AllArgsConstructor
@Service
@EnableBinding(EventSender.class)
public class DomainEventPublisher implements IDomainEventPublisher {
    private final EventSender eventSender;

    @Override
    @SendTo(ORDERS_TOPIC)
    public void publish(List<DomainEvent<?>> eventList) {
        eventList.forEach(event -> eventSender.orderEventOutbound()
                .send(MessageBuilder.
                        withPayload(event)
                        .build()));
    }
}
