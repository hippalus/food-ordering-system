package com.hippalus.orderingservice.domain.domainevents;

import com.hippalus.orderingservice.domain.exceptions.AggregateException;
import com.hippalus.orderingservice.domain.models.Orders;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static com.hippalus.orderingservice.BeanUtils.createOrderCommand;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderCreatedTest {

    @Test
    void whenTheOrderIsCreatedThenOrderCreatedEventMustBeCreated() throws AggregateException {
        //given:
        var createCmd = createOrderCommand();
        var expectedEvent=new OrderCreated<>(createCmd.getId(),createCmd.getCustomerId(),
                createCmd.getItems(), OffsetDateTime.now());
        //when:
        var order= Orders.create(createCmd);

        //then:
        assertThat(order.getDomainEvents().get(0))
                .isEqualToComparingOnlyGivenFields(expectedEvent,"entityId","customerId");
    }

    @Test
    void whenTheOrderIsCreatedThenOrderCreatedEventMustBeCreated2() throws AggregateException {
        //given:
        var createCmd = createOrderCommand();
        var expectedEvent=new OrderCreated<>();
        expectedEvent.setEntityId(createCmd.getId());
        expectedEvent.setOrderItems(createCmd.getItems());
        expectedEvent.setCustomerId(createCmd.getCustomerId());

        //when:
        var order= Orders.create(createCmd);

        //then:
        assertThat(order.getDomainEvents().get(0))
                .isEqualToComparingOnlyGivenFields(expectedEvent,"entityId","customerId");
    }
}
