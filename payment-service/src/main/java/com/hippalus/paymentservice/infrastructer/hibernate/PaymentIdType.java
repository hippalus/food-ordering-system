package com.hippalus.paymentservice.infrastructer.hibernate;

import com.hippalus.paymentservice.domain.models.PaymentId;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdCustomType;
import com.hippalus.sharedkernel.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class PaymentIdType extends DomainObjectIdCustomType<PaymentId>{
    private static final DomainObjectIdTypeDescriptor<PaymentId> TYPE_DESCRIPTOR=
            new DomainObjectIdTypeDescriptor<>(PaymentId.class,PaymentId::new);

    public PaymentIdType() {
        super(TYPE_DESCRIPTOR);
    }
}

