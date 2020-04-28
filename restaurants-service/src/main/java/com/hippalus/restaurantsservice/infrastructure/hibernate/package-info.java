@TypeDefs({
        @TypeDef(defaultForType = RestaurantId.class,typeClass = RestaurantIdType.class),
        @TypeDef(defaultForType = MenuId.class, typeClass = MenuIdType.class),
        @TypeDef(defaultForType = ProductId.class, typeClass = ProductIdType.class)
})
package com.hippalus.restaurantsservice.infrastructure.hibernate;


import com.hippalus.restaurantsservice.domain.models.MenuId;
import com.hippalus.restaurantsservice.domain.models.ProductId;
import com.hippalus.restaurantsservice.domain.models.RestaurantId;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;