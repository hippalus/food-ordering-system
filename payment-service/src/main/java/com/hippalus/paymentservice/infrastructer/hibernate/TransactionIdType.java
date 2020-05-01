package com.hippalus.paymentservice.infrastructer.hibernate;

import com.hippalus.paymentservice.domain.models.TransactionId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class TransactionIdType extends DomainObjectIdCustomType<TransactionId> {
    private static final DomainObjectIdTypeDescriptor<TransactionId> TYPE_DESCRIPTOR=
            new DomainObjectIdTypeDescriptor<>(TransactionId.class,TransactionId::new);

    public TransactionIdType() {
        super(TYPE_DESCRIPTOR);
    }
}
