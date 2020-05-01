package com.hippalus.paymentservice.infrastructer.hibernate;

import com.hippalus.paymentservice.domain.models.TransactionItemId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class TransactionItemIdType extends DomainObjectIdCustomType<TransactionItemId> {
    private static final DomainObjectIdTypeDescriptor<TransactionItemId> TYPE_DESCRIPTOR =
            new DomainObjectIdTypeDescriptor<>(TransactionItemId.class, TransactionItemId::new);

    public TransactionItemIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
