package com.hippalus.restaurantsservice.application.mapper;

import com.hippalus.restaurantsservice.application.request.CreateRestaurantRequest;
import com.hippalus.restaurantsservice.application.response.MenuResponse;
import com.hippalus.restaurantsservice.application.response.ProductResponse;
import com.hippalus.restaurantsservice.domain.commands.CreateRestaurant;
import com.hippalus.restaurantsservice.domain.models.Menu;
import com.hippalus.restaurantsservice.domain.models.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    public CreateRestaurant requestToCommand(CreateRestaurantRequest request) {
        if (request == null) {
            return null;
        } else {
            return CreateRestaurant.builder()
                    .name(request.getName())
                    .menus(this.menuResponseSetToMenuSet(request.getMenus()))
                    .build();
        }
    }

    public Set<Menu> menuResponseSetToMenuSet(Set<MenuResponse> set) {
        if (set == null) {
            return new HashSet<>();
        } else {
            return set.stream()
                    .map(this::menuResponseToMenu)
                    .collect(Collectors.toSet());

        }
    }

    public Menu menuResponseToMenu(MenuResponse menuResponse) {
        if (menuResponse == null) {
            return null;
        } else {
            Menu menu = new Menu();
            menu.setName(menuResponse.getName());
            menu.setMenuItems(this.productResponseSetToProductSet(menuResponse.getMenuItems()));
            return menu;
        }
    }

    public Set<Product> productResponseSetToProductSet(Set<ProductResponse> set) {
        if (set == null) {
            return new HashSet<>();
        } else {

            return set.stream()
                    .map(this::productResponseToProduct)
                    .collect(Collectors.toSet());
        }
    }

    public Product productResponseToProduct(ProductResponse productResponse) {
        if (productResponse == null) {
            return null;
        } else {
            return new Product(productResponse.getName(), productResponse.getPrice());
        }
    }


}
