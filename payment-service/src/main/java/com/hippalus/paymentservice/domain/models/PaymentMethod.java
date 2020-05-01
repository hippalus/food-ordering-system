package com.hippalus.paymentservice.domain.models;


import java.util.Optional;

public enum PaymentMethod {
    CREDITCARD;

    public static PaymentMethod of(String name){
        return Optional.of(name)
                .map(String::toUpperCase)
                .map(PaymentMethod::valueOf)
                .orElseThrow(NullPointerException::new);
    }
}
