package com.hippalus.restaurantsservice.domain.models;

import com.hippalus.sharedkernel.domain.AbstractEntity;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
public class Product extends AbstractEntity<ProductId> {

    private String name;
    private BigDecimal price;
    @ManyToMany(mappedBy = "menuItems")
    private final Set<Menu> menus=new HashSet<>();

    public Product() {
        super(DomainObjectId.randomId(ProductId.class));
    }
    public Product(ProductId id,String name,BigDecimal price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}


