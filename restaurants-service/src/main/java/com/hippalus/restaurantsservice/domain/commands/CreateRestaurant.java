package com.hippalus.restaurantsservice.domain.commands;

import com.hippalus.restaurantsservice.domain.models.Menu;
import com.hippalus.restaurantsservice.domain.models.RestaurantId;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurant {

    @Builder.Default
    private RestaurantId id= DomainObjectId.randomId(RestaurantId.class);
    private String name;
    @Builder.Default
    private Set<Menu> menus=new HashSet<>();

}
