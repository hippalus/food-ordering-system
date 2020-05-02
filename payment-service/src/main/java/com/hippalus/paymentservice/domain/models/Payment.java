package com.hippalus.paymentservice.domain.models;

import com.hippalus.paymentservice.domain.commands.PayCommand;
import com.hippalus.paymentservice.domain.domainevents.PaymentFailedEvent;
import com.hippalus.paymentservice.domain.domainevents.PaymentSuccessEvent;
import com.hippalus.sharedkernel.domain.AbstractAggregateRoot;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import java.time.OffsetDateTime;


@EqualsAndHashCode(callSuper = false)
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends AbstractAggregateRoot<PaymentId> {

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod type;
    @CreditCardNumber(ignoreNonDigitCharacters = true)
    private String cardNumber;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Transaction transaction;
    private OffsetDateTime createdDate;

    @Builder
    public Payment(PaymentMethod type, String cardNumber, Transaction transaction) {
        super(DomainObjectId.randomId(PaymentId.class));
        this.type = type;
        this.createdDate = OffsetDateTime.now();
        this.cardNumber = cardNumber;
        this.transaction = transaction;

    }

    public static Payment pay(PayCommand cmd) {
        return Payment.builder()
                .cardNumber(cmd.getCardNumber())
                .transaction(cmd.getTransaction())
                .type(cmd.getPaymentMethod())
                .build();
    }

    public void success() {
        this.setStatus(PaymentStatus.SUCCESS);
        PaymentSuccessEvent.PaymentSuccessEventBuilder<PaymentId> successEventBuilder = PaymentSuccessEvent.builder();
        this.applyEvent(successEventBuilder.paymentId(this.getId())
                .transactionId(this.getTransaction().getId().toUUID())
                .amount(this.getTransaction().getAmount())
                .createdDate(OffsetDateTime.now())
                .paymentMethod(this.getType())
                .build());
    }

    public void failed() {
        this.setStatus(PaymentStatus.FAILED);
        PaymentFailedEvent.PaymentFailedEventBuilder<PaymentId> failedEventBuilder = PaymentFailedEvent.builder();
        this.applyEvent(failedEventBuilder.paymentId(this.getId())
                .amount(this.getTransaction().getAmount())
                .transactionId(this.getTransaction().getId().toUUID())
                .createdDate(OffsetDateTime.now())
                .build());
    }
}
