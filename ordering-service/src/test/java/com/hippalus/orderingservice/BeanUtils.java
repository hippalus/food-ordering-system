package com.hippalus.orderingservice;

import com.hippalus.orderingservice.domain.commands.CreateOrder;
import com.hippalus.orderingservice.domain.models.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public final class BeanUtils {
    private BeanUtils() {
        //DO NOTING
    }
    public static Orders newOrder() {
        return new Orders(createOrderCommand().getId(),createOrderCommand().getCustomerId(),
                createOrderCommand().getItems(), OffsetDateTime.now());
    }

    public static CreateOrder createOrderCommand() {
        return CreateOrder.builder()
                .id(new OrderId(UUID.randomUUID().toString()))
                .customerId("Jone")
                .items(getTestItemsz())
                .build();
    }

    public static Set<OrderItem> getTestItems() {
        var lahmacun = new OrderItem(new ProductId("lahmacun"), 3,
                "Description",BigDecimal.TEN);

        var salgam =  new OrderItem(new ProductId("salgam"), 1,
                "Description",BigDecimal.valueOf(4.99));

        Set<OrderItem> items =new HashSet<>();
        items.add(salgam);
        items.add(lahmacun);
        return items;
    }
    public static Set<OrderItem> getTestItemsz() {
        var lahmacun = OrderItem.builder()
                .productId(new ProductId("lahmacun"))
                .price(BigDecimal.TEN)
                .quantity(3)
                .build();
        var salgam = OrderItem.builder()
                .productId(new ProductId("salgam"))
                .price(BigDecimal.valueOf(4.99))
                .quantity(1)
                .build();

        Set<OrderItem> items =new HashSet<>();
        items.add(salgam);
        items.add(lahmacun);
        return items;
    }
}
