package com.hippalus.orderingservice.domain.exceptions;

import com.hippalus.orderingservice.domain.policies.OrderErrorCode;
import com.hippalus.sharedkernel.domain.DomainException;

public class OrderIdIsNullException extends DomainException {
    public OrderIdIsNullException(String source, OrderErrorCode errorCode, String errorMessage) {
        super(source, errorCode, errorMessage);
    }
}
