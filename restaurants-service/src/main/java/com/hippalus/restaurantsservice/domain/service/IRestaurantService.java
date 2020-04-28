package com.hippalus.restaurantsservice.domain.service;

import com.hippalus.restaurantsservice.application.request.CreateRestaurantRequest;
import com.hippalus.restaurantsservice.application.response.RestaurantResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.*;

public interface IRestaurantService {
    @Transactional
    RestaurantResponse createRestaurant(CreateRestaurantRequest request);

    @Transactional(isolation = READ_COMMITTED)
    List<RestaurantResponse> getAllRestaurants();
}
