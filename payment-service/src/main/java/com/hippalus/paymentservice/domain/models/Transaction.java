package com.hippalus.paymentservice.domain.models;

import com.hippalus.sharedkernel.domain.AbstractEntity;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
public class Transaction extends AbstractEntity<TransactionId> {

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transaction_id", nullable = false)
    private List<TransactionItem> items;
    @PositiveOrZero
    private BigDecimal amount;

    public Transaction() {
        super(DomainObjectId.randomId(TransactionId.class));
        this.items=new ArrayList<>();
    }

    public Transaction(TransactionId id,List<TransactionItem> items) {
        super(id);
        this.items=items;
        this.amount=this.items.stream()
                .map(TransactionItem::fee)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }


}
