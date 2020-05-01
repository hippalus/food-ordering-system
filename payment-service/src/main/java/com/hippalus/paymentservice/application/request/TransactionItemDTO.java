package com.hippalus.paymentservice.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItemDTO {
    private String id;
    private int quantity;
    private String itemDescription;
    private BigDecimal price;
}
