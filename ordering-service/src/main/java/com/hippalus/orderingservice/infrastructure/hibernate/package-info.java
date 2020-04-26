@TypeDefs({
        @TypeDef(defaultForType = OrderId.class,typeClass = OrderIdType.class),
        @TypeDef(defaultForType = OrderItemId.class, typeClass = OrderItemIdType.class)
})
package com.hippalus.orderingservice.infrastructure.hibernate;

import com.hippalus.orderingservice.domain.models.OrderId;
import com.hippalus.orderingservice.domain.models.OrderItemId;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;