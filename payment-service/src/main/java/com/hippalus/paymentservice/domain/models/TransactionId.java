package com.hippalus.paymentservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class TransactionId extends DomainObjectId {
    public TransactionId(String uuid) {
        super(uuid);
    }
}
