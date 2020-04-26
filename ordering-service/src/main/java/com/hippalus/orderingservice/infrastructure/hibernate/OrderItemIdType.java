package com.hippalus.orderingservice.infrastructure.hibernate;


import com.hippalus.orderingservice.domain.models.OrderItemId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class OrderItemIdType extends DomainObjectIdCustomType<OrderItemId> {
    private static final DomainObjectIdTypeDescriptor<OrderItemId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
            OrderItemId.class, OrderItemId::new);

    public OrderItemIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
