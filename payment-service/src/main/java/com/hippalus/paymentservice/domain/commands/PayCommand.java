package com.hippalus.paymentservice.domain.commands;

import com.hippalus.paymentservice.domain.models.PaymentMethod;
import com.hippalus.paymentservice.domain.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PayCommand {
    private String cardNumber;
    private Transaction transaction;
    private PaymentMethod paymentMethod;

}
