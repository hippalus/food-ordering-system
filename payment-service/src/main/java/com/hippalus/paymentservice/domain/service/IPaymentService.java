package com.hippalus.paymentservice.domain.service;

import com.hippalus.paymentservice.application.request.PaymentRequest;
import com.hippalus.paymentservice.application.response.PaymentResponse;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.*;

public interface IPaymentService {

    @Transactional(propagation = REQUIRES_NEW)
    PaymentResponse pay(PaymentRequest request);
}
