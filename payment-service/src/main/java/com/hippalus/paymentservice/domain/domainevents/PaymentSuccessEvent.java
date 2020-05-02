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
public class PaymentSuccessEvent<T extends DomainObjectId> extends DomainEvent<T> {

    private String transactionId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private OffsetDateTime createdDate;
    @Builder
    public PaymentSuccessEvent(T paymentId,String transactionId,PaymentMethod paymentMethod,
                               BigDecimal amount, OffsetDateTime createdDate) {
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.setEntityId(paymentId);
        this.amount = amount;
        this.createdDate = createdDate;
        this.setEventType(PaymentEventType.PAYMENT_SUCCESS);
    }
}
