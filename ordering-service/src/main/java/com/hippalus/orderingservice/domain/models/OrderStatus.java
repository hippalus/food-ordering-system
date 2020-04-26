package com.hippalus.orderingservice.domain.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    CREATED(1), COMPLETED(2), CANCELED(3);

    private static final Map<Integer, OrderStatus> map = new HashMap<>();

    static {
        Arrays.stream(OrderStatus.values())
                .forEach(pageType -> map.put(pageType.value, pageType));
    }

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
