package com.hippalus.restaurantsservice.infrastructure.hibernate;


import com.hippalus.restaurantsservice.domain.models.MenuId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class MenuIdType extends DomainObjectIdCustomType<MenuId> {
    private static final DomainObjectIdTypeDescriptor<MenuId> TYPE_DESCRIPTOR=
            new DomainObjectIdTypeDescriptor<>(MenuId.class,MenuId::new);

    public MenuIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
