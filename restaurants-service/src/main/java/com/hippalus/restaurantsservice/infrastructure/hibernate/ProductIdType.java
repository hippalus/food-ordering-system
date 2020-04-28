package com.hippalus.restaurantsservice.infrastructure.hibernate;

import com.hippalus.restaurantsservice.domain.models.ProductId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class ProductIdType extends DomainObjectIdCustomType<ProductId> {
    private static final DomainObjectIdTypeDescriptor<ProductId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(ProductId.class, ProductId::new);

    public ProductIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
