package com.hippalus.restaurantsservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class ProductId extends DomainObjectId {
    public ProductId(String uuid) {
        super(uuid);
    }
}
