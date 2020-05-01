package com.hippalus.paymentservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class PaymentId extends DomainObjectId {
    public PaymentId(String uuid) {
        super(uuid);
    }
}
