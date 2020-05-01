package com.hippalus.paymentservice.domain.models;

import com.hippalus.sharedkernel.domain.DomainObjectId;

public class TransactionItemId extends DomainObjectId {
    public TransactionItemId(String uuid) {
        super(uuid);
    }
}
