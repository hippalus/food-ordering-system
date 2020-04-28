package com.hippalus.restaurantsservice.application.response;

import com.hippalus.restaurantsservice.domain.models.Restaurant;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

    private String id;
    private String name;
    private Set<MenuResponse> menus = new HashSet<>();

    public RestaurantResponse(Restaurant restaurant) {
        this.setId(restaurant.getId().toUUID());
        this.setName(restaurant.getName());
        final var menuResponse = restaurant.getMenus()
                .stream()
                .map(MenuResponse::new)
                .collect(Collectors.toSet());
        this.setMenus(menuResponse);
    }
}
