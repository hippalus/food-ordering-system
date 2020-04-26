package com.hippalus.orderingservice.domain.domainevents;

import com.hippalus.orderingservice.domain.exceptions.AggregateException;
import com.hippalus.orderingservice.domain.models.OrderStatus;
import com.hippalus.orderingservice.domain.models.Orders;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static com.hippalus.orderingservice.BeanUtils.createOrderCommand;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusChangedTest {

    @Test
    void whenTheOrderStatusIsChangedThenOrderStatusChangedEventMustBeCreated() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);
        var changedEvent = new OrderStatusChanged<>(order.getId(), OrderStatus.CREATED,
                OrderStatus.COMPLETED, OffsetDateTime.now());
        //when:
        order.completed();

        //then:
        assertThat(order.getDomainEvents().get(1))
                .isEqualToComparingOnlyGivenFields(changedEvent,
                        "lastStatus", "curStatus", "entityId");

        order.getDomainEvents().forEach(System.err::println);
    }

    @Test
    void whenTheOrderStatusIsChangedThenOrderStatusChangedEventMustBeCreated2() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);
        order.completed();
        var changedEvent = new OrderStatusChanged<>();
        changedEvent.setEntityId(order.getId());
        changedEvent.setLastStatus(OrderStatus.COMPLETED);
        changedEvent.setCurStatus(OrderStatus.CANCELED);

        //when:
        order.cancel();

        //then:
        assertThat(order.getDomainEvents().get(2))
                .isEqualToComparingOnlyGivenFields(changedEvent,
                        "lastStatus", "curStatus", "entityId");

        order.getDomainEvents().forEach(System.err::println);
    }

}
