package com.hippalus.orderingservice.application.service;

import com.hippalus.orderingservice.domain.models.OrderId;
import com.hippalus.orderingservice.domain.models.Orders;
import com.hippalus.orderingservice.domain.paymentevents.IPaymentEventHandler;
import com.hippalus.orderingservice.domain.paymentevents.PaymentEvent;
import com.hippalus.orderingservice.domain.repository.OrderRepository;
import com.hippalus.orderingservice.infrastructure.kafka.EventListener;
import com.hippalus.sharedkernel.utilities.DomainModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.hippalus.orderingservice.infrastructure.kafka.EventListener.*;
import static org.springframework.transaction.annotation.Isolation.*;


@Service
@EnableBinding(EventListener.class)
@RequiredArgsConstructor
public class PaymentEventHandler implements IPaymentEventHandler {

    private final OrderRepository repository;
    private final DomainEventPublisher eventPublisher;


    //TODO Switch Case Replace Conditional with Polymorphism.
    //@StreamListener(target = EventListener.PAYMENT_TOPIC,condition = "'payload.eventType==PAYMENT_SUCCESS'")
    @StreamListener(target = PAYMENT_TOPIC)
    @Transactional
    @Override
    public void handleEvent(String paymentEvent) {
        final var payment = DomainModelMapper.readValue(paymentEvent, PaymentEvent.class);
        Optional<Orders> order = getOrderById(payment);
        order.ifPresent(orders -> {
            switch (payment.getEventType()) {
                case PAYMENT_FAILED:
                    orders.cancel();
                    eventPublisher.publish(orders.getDomainEvents());
                    repository.saveAndFlush(orders);
                    break;
                case PAYMENT_SUCCESS:
                    orders.completed();
                    eventPublisher.publish(orders.getDomainEvents());
                    repository.saveAndFlush(orders);
                    break;
                default:
                    throw new IllegalStateException();
            }
        });

    }


    @Transactional(isolation = READ_COMMITTED)
    public Optional<Orders> getOrderById(PaymentEvent payment) {
        return repository.findById(new OrderId(payment.getTransactionId()));
    }
}