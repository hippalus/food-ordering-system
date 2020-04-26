package com.hippalus.orderingservice.domain.exceptions;

import com.hippalus.orderingservice.domain.policies.OrderErrorCode;
import com.hippalus.sharedkernel.domain.DomainException;

public class CustomerIdIsNullException extends DomainException {
    public CustomerIdIsNullException(String source, OrderErrorCode errorCode, String errorMessage) {
        super(source, errorCode, errorMessage);
    }
}
