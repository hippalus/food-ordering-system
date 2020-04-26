package com.hippalus.orderingservice.domain.commands;


import com.hippalus.orderingservice.domain.models.OrderId;
import com.hippalus.orderingservice.domain.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class CreateOrder {

    private OrderId id;
    private String customerId;
    private Set<OrderItem> items;
}
