package com.hippalus.paymentservice.domain.models;

public enum  PaymentStatus {
    SUCCESS("SUCCESS"), FAILED("FAILED");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
