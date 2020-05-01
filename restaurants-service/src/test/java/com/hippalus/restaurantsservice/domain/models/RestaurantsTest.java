package com.hippalus.restaurantsservice.domain.models;

import com.hippalus.restaurantsservice.domain.commands.CreateRestaurant;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantsTest {

    @Test
    void restaurantShouldBeCreatedWithTheCreateRestaurantCommand(){
        //given:
        var products = newProduct();
        var menus = newMenu(products);
        var expected= newRestaurant(menus);
        var cmd= CreateRestaurant.builder()
                .id(new RestaurantId("1"))
                .name("Dayinin Yeri")
                .menus(menus)
                .build();

        //when:
        final var restaurant = Restaurant.create(cmd);

        //then:
        assertThat(restaurant).isEqualTo(expected);
    }


    private Restaurant newRestaurant(Set<Menu> menus) {
        return new Restaurant(new RestaurantId("1"),"Dayinin Yeri",menus);
    }

    private Set<Menu> newMenu(Set<Product> products) {
        Set<Menu> menus=new HashSet<>();
        menus.add(new Menu("Izgara Cesitleri",products));
        return menus;
    }

    private Set<Product> newProduct() {
        Set<Product> products=new HashSet<>();
        products.add(new Product("Kebap", BigDecimal.valueOf(30)));
        return products;
    }


}