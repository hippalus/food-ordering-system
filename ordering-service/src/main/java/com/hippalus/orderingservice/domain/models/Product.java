package com.hippalus.orderingservice.domain.models;


import com.hippalus.sharedkernel.domain.ValueObject;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@EqualsAndHashCode
public class Product implements ValueObject {

    private final ProductId id;
    private final String name;
    private final BigDecimal price;

    private Product(ProductId id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product of(ProductId id, String name, BigDecimal price) {
        return new Product(id, name, price);
    }
}
