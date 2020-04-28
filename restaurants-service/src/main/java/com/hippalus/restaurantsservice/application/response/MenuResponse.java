package com.hippalus.restaurantsservice.application.response;


import com.hippalus.restaurantsservice.domain.models.Menu;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private String id;
    private String name;
    private Set<ProductResponse> menuItems = new HashSet<>();

    public MenuResponse(Menu menu) {
        this.setId(menu.getId().toUUID());
        this.setName(menu.getName());
        final var productResponses = menu.getMenuItems()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toSet());
        this.setMenuItems(productResponses);
    }
}
