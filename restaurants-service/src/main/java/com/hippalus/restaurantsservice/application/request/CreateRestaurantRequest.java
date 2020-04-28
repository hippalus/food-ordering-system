package com.hippalus.restaurantsservice.application.request;

import com.hippalus.restaurantsservice.application.response.MenuResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {
    private String name;
    private Set<MenuResponse> menus = new HashSet<>();
}
