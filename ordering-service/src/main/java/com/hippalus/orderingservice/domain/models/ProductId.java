package com.hippalus.orderingservice.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hippalus.sharedkernel.domain.DomainObjectId;

public class ProductId extends DomainObjectId {
    @JsonCreator
    public ProductId(String uuid) {
        super(uuid);
    }
}