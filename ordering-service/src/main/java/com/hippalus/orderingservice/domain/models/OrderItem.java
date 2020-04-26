package com.hippalus.orderingservice.domain.models;

import com.hippalus.sharedkernel.domain.AbstractEntity;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@NoArgsConstructor
@Builder
@Entity
public class OrderItem extends AbstractEntity<OrderItemId> {

    private ProductId productId;
    private int quantity;
    private String itemDescription;
    private BigDecimal price;

    public OrderItem(ProductId productId, int quantity, String itemDescription, BigDecimal price) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.quantity = quantity;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public BigDecimal fee() {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }
}
