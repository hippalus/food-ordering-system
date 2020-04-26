package com.hippalus.orderingservice.domain.models;

import com.hippalus.orderingservice.domain.commands.CreateOrder;
import com.hippalus.orderingservice.domain.exceptions.AggregateException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.hippalus.orderingservice.BeanUtils.createOrderCommand;
import static com.hippalus.orderingservice.BeanUtils.newOrder;
import static org.assertj.core.api.Assertions.*;

public class OrdersTest {
    @Test
    void initialOrder() {
        var order = new Orders();
        assertThat(order).isNotNull();
    }

    @Test
    void orderShouldBeCreatedWithTheCreateOrderCommand() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var expected = newOrder();

        //when:
        var order = Orders.create(cmd);

        //then:
        assertThat(order).isEqualToIgnoringGivenFields(expected,
                "id", "statusChangeHistory", "createdDate", "domainEvents");

    }

    @Test
    void whenOrderIsVerifiedIfOrderIdIsNullThrowAggregateException() {
        //given:
        var cmd = new CreateOrder();

        assertThatThrownBy(() -> Orders.create(cmd))
                .isInstanceOf(AggregateException.class);

    }

    @Test
    void whenOrderIsVerifiedIfOrderIdIsNullThrowAggregateExceptionWithMessage() {
        //given:
        var cmd = new CreateOrder();

        try {
            //when:
            Orders.create(cmd);
            fail("Test Failed");
        } catch (AggregateException e) {
            //then:
            System.err.println(e.getMessage());
        }
    }

    @Test
    void totalFeeShouldBeCalculatedWhenTheOrderIsCreated() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);

        //when:
        final var totalFee = order.totalFee();

        //then:
        assertThat(totalFee).isEqualTo(BigDecimal.valueOf(34.99));
    }


    @Test
    void itemsShouldBeAddedToTheOrder() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);
        var item=Product.of(new ProductId("Baklava"),"Antep Baklava",BigDecimal.valueOf(15));

        //when:
        order.addItem(item,1);

        //then:
        assertThat(order.getOrderItems().size()).isEqualTo(3);

    }

    @Test
    void shouldBeModifiedDateWhenOrderStatusChanges() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);

        //when:
        order.completed();

        //then:
        assertThat(order.getModifiedDate()).isNotNull();
    }

    @Test
    void shouldBeCompleted() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);

        //when:
        order.completed();

        //then:
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void shouldBeCanceled() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);

        //when:
        order.cancel();

        //then:
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELED);
    }


    @Test
    void shouldBeStatusChangedHistory() throws AggregateException {
        //given:
        var cmd = createOrderCommand();
        var order = Orders.create(cmd);
        Set<OrderStatusChange> orderStatusChanges=new HashSet<>();
        orderStatusChanges.add(new OrderStatusChange(OffsetDateTime.now(),OrderStatus.COMPLETED));
        orderStatusChanges.add(new OrderStatusChange(OffsetDateTime.now(),OrderStatus.CANCELED));

        //when:
        order.completed();
        order.cancel();

        //then:
        assertThat(order.getStatusChangeHistory().size())
                .isEqualTo(orderStatusChanges.size());
    }



}
