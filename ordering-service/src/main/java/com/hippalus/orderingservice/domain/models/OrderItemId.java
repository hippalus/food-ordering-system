package com.hippalus.orderingservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class OrderItemId extends DomainObjectId {
    public OrderItemId(String uuid) {
        super(uuid);
    }
}
