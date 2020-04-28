package com.hippalus.restaurantsservice.application.response;

import com.hippalus.restaurantsservice.domain.models.Product;
import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal price;

    public ProductResponse(Product product) {
        this.setId(product.getId().toUUID());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
    }
}
