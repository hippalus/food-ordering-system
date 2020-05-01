package com.hippalus.paymentservice.domain.exceptions;

import com.hippalus.paymentservice.domain.polices.PaymentErrorCode;
import com.hippalus.sharedkernel.domain.DomainException;

public class InvalidCardException extends DomainException {

    public InvalidCardException(String source, PaymentErrorCode errorCode, String message) {
        super(source, errorCode, message);
    }


}
