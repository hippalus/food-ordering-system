package com.hippalus.restaurantsservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class RestaurantId extends DomainObjectId {
    public RestaurantId(String uuid) {
        super(uuid);
    }
}
