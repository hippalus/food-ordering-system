package com.hippalus.restaurantsservice.infrastructure.hibernate;


import com.hippalus.restaurantsservice.domain.models.RestaurantId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class RestaurantIdType extends DomainObjectIdCustomType<RestaurantId> {
    private static final DomainObjectIdTypeDescriptor<RestaurantId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
            RestaurantId.class, RestaurantId::new);

    public RestaurantIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
