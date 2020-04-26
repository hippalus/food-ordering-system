package com.hippalus.orderingservice.domain.exceptions;

import java.util.List;

public class AggregateException extends Exception {
    private final List<Exception> secondaryExceptions;

    public AggregateException(List<Exception> exceptions) {
        this.secondaryExceptions = exceptions;
    }

    @Override
    public String getMessage() {
        var stringBuilder = new StringBuilder();
        secondaryExceptions.forEach(e -> stringBuilder.append(e.toString()).append("\n"));
        return stringBuilder.toString();
    }
}
