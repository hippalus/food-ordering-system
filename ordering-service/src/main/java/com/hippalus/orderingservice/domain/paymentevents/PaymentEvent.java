package com.hippalus.orderingservice.domain.paymentevents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String entityId;
    private String eventId;
    private String transactionId;
    private PaymentEventType eventType;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private OffsetDateTime createdDate;
    private OffsetDateTime occurredDate;
}


