package com.hippalus.restaurantsservice.application.service;

import com.hippalus.restaurantsservice.application.request.CreateRestaurantRequest;
import com.hippalus.restaurantsservice.application.response.RestaurantResponse;
import com.hippalus.restaurantsservice.application.mapper.RestaurantMapper;
import com.hippalus.restaurantsservice.domain.models.*;
import com.hippalus.restaurantsservice.domain.repository.RestaurantRepository;
import com.hippalus.restaurantsservice.domain.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    @Override
    @Transactional
    public RestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        final var cmd = mapper.requestToCommand(request);
        final var restaurant = Restaurant.create(cmd);
        final var persistRestaurant = repository.saveAndFlush(restaurant);
        return new RestaurantResponse(persistRestaurant);
    }

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public List<RestaurantResponse> getAllRestaurants() {
        return repository.findAll()
                .stream()
                .map(RestaurantResponse::new)
                .collect(Collectors.toList());
    }
}
