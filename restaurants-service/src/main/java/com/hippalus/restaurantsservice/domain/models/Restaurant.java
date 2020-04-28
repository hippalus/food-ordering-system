package com.hippalus.restaurantsservice.domain.models;

import com.hippalus.restaurantsservice.domain.commands.CreateRestaurant;
import com.hippalus.sharedkernel.domain.AbstractAggregateRoot;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
public class Restaurant extends AbstractAggregateRoot<RestaurantId> {

    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    private Set<Menu> menus =new HashSet<>();

    public Restaurant() {
        super(DomainObjectId.randomId(RestaurantId.class));
    }
    public Restaurant(RestaurantId id,String name, Set<Menu> menus) {
        super(id);
        this.name = name;
        this.menus = menus;
    }

    public static Restaurant create(CreateRestaurant cmd) {
        return new Restaurant(cmd.getId(),cmd.getName(),cmd.getMenus());
    }
}
