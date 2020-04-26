package com.hippalus.orderingservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class OrderId extends DomainObjectId {
    public OrderId(String uuid) {
        super(uuid);
    }

}
