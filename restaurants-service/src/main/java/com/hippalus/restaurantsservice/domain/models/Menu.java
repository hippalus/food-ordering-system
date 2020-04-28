package com.hippalus.restaurantsservice.domain.models;

import com.hippalus.sharedkernel.domain.AbstractEntity;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity
public class Menu extends AbstractEntity<MenuId> {

    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "menu_product",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> menuItems=new HashSet<>();

    public Menu() {
        super(DomainObjectId.randomId(MenuId.class));
    }

    public Menu(MenuId id,String name,Set<Product> menuItems) {
        super(id);
        this.name=name;
        this.menuItems=menuItems;
    }
}
