package com.hippalus.restaurantsservice.domain.repository;

import com.hippalus.restaurantsservice.domain.models.Restaurant;
import com.hippalus.restaurantsservice.domain.models.RestaurantId;
import com.hippalus.sharedkernel.infrastructure.jpa.IJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends IJpaRepository<Restaurant,RestaurantId> {
}
