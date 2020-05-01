package com.hippalus.paymentservice.application.web;

import com.hippalus.paymentservice.application.request.PaymentRequest;
import com.hippalus.paymentservice.application.response.PaymentResponse;
import com.hippalus.paymentservice.domain.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentResource  {
    private final IPaymentService paymentService;

    @PostMapping(value = "/payment/pay",consumes = "application/json", produces = "application/json")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody PaymentRequest request) {
        return  ResponseEntity.ok(paymentService.pay(request));
    }
}
