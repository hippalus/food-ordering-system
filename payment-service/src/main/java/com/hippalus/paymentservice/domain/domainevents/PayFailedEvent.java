package com.hippalus.paymentservice.domain.domainevents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.hippalus.paymentservice.domain.models.PaymentMethod;
import com.hippalus.sharedkernel.domain.DomainEvent;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PayFailedEvent<T extends DomainObjectId> extends DomainEvent<T> {

    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private OffsetDateTime createdDate;
    @Builder
    public PayFailedEvent(T paymentId, BigDecimal amount, OffsetDateTime createdDate) {
        this.setEntityId(paymentId);
        this.amount = amount;
        this.createdDate = createdDate;
        this.setEventType(PaymentEventType.PAYMENT_FAILED);
    }
}
