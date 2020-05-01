package com.hippalus.paymentservice.domain.models;

import com.hippalus.sharedkernel.domain.AbstractEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
public class TransactionItem extends AbstractEntity<TransactionItemId> {

    @Min(1)
    private int quantity;
    private String itemDescription;
    private BigDecimal price;

    @Builder
    public TransactionItem(TransactionItemId id, int quantity, String itemDescription, BigDecimal price) {
        this.setId(id);
        this.quantity = quantity;
        this.itemDescription = itemDescription;
        this.price = price;
    }
    public BigDecimal fee() {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }
}
