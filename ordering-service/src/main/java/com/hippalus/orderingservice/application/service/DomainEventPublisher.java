package com.hippalus.orderingservice.application.service;

import com.hippalus.orderingservice.domain.domainevents.IDomainEventPublisher;
import com.hippalus.orderingservice.infrastructure.kafka.EventSender;
import com.hippalus.sharedkernel.domain.DomainEvent;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hippalus.orderingservice.infrastructure.kafka.EventSender.ORDERS_TOPIC;


@Component
@EnableBinding(EventSender.class)
public class DomainEventPublisher implements IDomainEventPublisher {
    private  MessageChannel eventSender;

    public DomainEventPublisher(EventSender eventSender) {
        this.eventSender = eventSender.orderEventOutbound();
    }

    @Override
    @SendTo(value = ORDERS_TOPIC)
    public void publish(List<DomainEvent<?extends DomainObjectId>> eventList) {
        eventList.forEach(event -> eventSender.send(MessageBuilder.withPayload(event).build()));
    }
}
