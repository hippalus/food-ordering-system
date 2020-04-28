package com.hippalus.restaurantsservice.application.mapper;

import com.hippalus.restaurantsservice.application.request.CreateRestaurantRequest;
import com.hippalus.restaurantsservice.domain.commands.CreateRestaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mappings({
            @Mapping(source = "request.name", target = "name"),
            @Mapping(source = "request.menus", target = "menus") })
    CreateRestaurant requestToCommand(CreateRestaurantRequest request);
}
