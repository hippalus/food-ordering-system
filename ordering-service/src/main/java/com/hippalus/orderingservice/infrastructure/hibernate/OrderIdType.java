package com.hippalus.orderingservice.infrastructure.hibernate;

import com.hippalus.orderingservice.domain.models.OrderId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class OrderIdType extends DomainObjectIdCustomType<OrderId> {
    private static final DomainObjectIdTypeDescriptor<OrderId> TYPE_DESCRIPTOR=
            new DomainObjectIdTypeDescriptor<>(OrderId.class,OrderId::new);

    public OrderIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
