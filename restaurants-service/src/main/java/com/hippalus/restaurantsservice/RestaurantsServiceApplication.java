package com.hippalus.restaurantsservice;

import com.hippalus.restaurantsservice.domain.models.Menu;
import com.hippalus.restaurantsservice.domain.models.Product;
import com.hippalus.restaurantsservice.domain.models.Restaurant;
import com.hippalus.restaurantsservice.domain.models.RestaurantId;
import com.hippalus.restaurantsservice.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
public class RestaurantsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsServiceApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    public class DataGenerator {
        final RestaurantRepository repository;

        @PostConstruct
        public void insert() {
            Restaurant restaurant = newRestaurants(newMenu(newProduct()));
            repository.saveAndFlush(restaurant);
        }


        private Restaurant newRestaurants(Set<Menu> menus) {
            return new Restaurant(new RestaurantId("1"), "Dayinin Yeri", menus);
        }

        private Set<Menu> newMenu(Set<Product> products) {
            Set<Menu> menus = new HashSet<>();
            menus.add(new Menu("Izgara Cesitleri", products));
            return menus;
        }

        private Set<Product> newProduct() {
            Set<Product> products = new HashSet<>();
            products.add(new Product("Adana Kebap", BigDecimal.valueOf(35.99)));
            products.add((new Product("Urfa Kebap", BigDecimal.valueOf(35.99))));
            products.add((new Product("Beyti", BigDecimal.valueOf(40))));
            return products;
        }
    }
}
