package com.hippalus.paymentservice.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private String id;//order id
    private String cardNumber;
    private List<TransactionItemDTO> items;
    private String paymentMethod;
}
