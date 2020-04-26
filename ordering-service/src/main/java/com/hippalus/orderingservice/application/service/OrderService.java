package com.hippalus.orderingservice.application.service;
import com.hippalus.orderingservice.application.request.CreateOrderRequest;
import com.hippalus.orderingservice.application.response.OrderResponse;
import com.hippalus.orderingservice.domain.commands.CreateOrder;
import com.hippalus.orderingservice.domain.domainevents.IDomainEventPublisher;
import com.hippalus.orderingservice.domain.models.OrderId;
import com.hippalus.orderingservice.domain.models.OrderItem;
import com.hippalus.orderingservice.domain.models.Orders;
import com.hippalus.orderingservice.domain.models.ProductId;
import com.hippalus.orderingservice.domain.repository.OrderRepository;
import com.hippalus.orderingservice.domain.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final IDomainEventPublisher eventPublisher;
    private final Validator validator;

    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) throws Exception {

        Objects.requireNonNull(request, "request must not be null");
        var constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException("The CreateOrderRequest is not valid", constraintViolations);
        }
        var createOrderCmd = transformToCreateOrderCmd(request);
        final var createdOrder = Orders.create(createOrderCmd);
        final var persistOrder = orderRepository.saveAndFlush(createdOrder);
        eventPublisher.publish(persistOrder.getDomainEvents());
        return new OrderResponse(persistOrder);
    }


    private CreateOrder transformToCreateOrderCmd(CreateOrderRequest request) {
        var orderItems = request.getItems()
                .stream()
                .map(itm -> new OrderItem(new ProductId(itm.getProductId()), itm.getQuantity(),
                        itm.getItemDescription(), itm.getPrice()))
                .collect(Collectors.toSet());
        return CreateOrder.builder()
                .id(new OrderId(UUID.randomUUID().toString()))
                .customerId(request.getCustomerId())
                .items(orderItems)
                .build();
    }
}