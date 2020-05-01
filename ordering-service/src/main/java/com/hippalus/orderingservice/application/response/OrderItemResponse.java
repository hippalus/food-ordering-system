package com.hippalus.orderingservice.application.response;

import com.hippalus.orderingservice.domain.models.OrderItem;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private String productId;
    private int quantity;
    private String itemDescription;
    private BigDecimal price;
    private BigDecimal fee;

    public OrderItemResponse(String productId, int quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.fee = this.price.multiply(BigDecimal.valueOf(quantity));
    }
    public OrderItemResponse(OrderItem orderItem) {
        this.productId = orderItem.getProductId().toUUID();
        this.quantity = orderItem.getQuantity();
        this.itemDescription=orderItem.getItemDescription();
        this.price = orderItem.getPrice();
        this.fee = orderItem.fee();
    }
}
