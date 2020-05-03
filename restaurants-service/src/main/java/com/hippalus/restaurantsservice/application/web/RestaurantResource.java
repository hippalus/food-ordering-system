package com.hippalus.restaurantsservice.application.web;

import com.hippalus.restaurantsservice.application.request.CreateRestaurantRequest;
import com.hippalus.restaurantsservice.application.response.RestaurantResponse;
import com.hippalus.restaurantsservice.domain.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantResource {
    private final IRestaurantService restaurantService;

    @PostMapping(value = "/restaurants/create",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantService.createRestaurant(request));
    }

    @GetMapping(value = "/restaurants/getall",produces = "application/json")
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
}
