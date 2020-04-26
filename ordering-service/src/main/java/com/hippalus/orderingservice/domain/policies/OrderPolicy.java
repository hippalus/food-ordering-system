package com.hippalus.orderingservice.domain.policies;

import com.hippalus.orderingservice.domain.exceptions.AggregateException;
import com.hippalus.orderingservice.domain.exceptions.CustomerIdIsNullException;
import com.hippalus.orderingservice.domain.exceptions.OrderIdIsNullException;
import com.hippalus.orderingservice.domain.exceptions.OrderItemEmptyException;
import com.hippalus.orderingservice.domain.models.OrderItem;
import com.hippalus.orderingservice.domain.models.Orders;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OrderPolicy {

    private OrderPolicy() {
        //DO NOTING
    }

    public static void verify(Orders order) throws AggregateException {
        List<Exception> exceptions = new ArrayList<>();
        if (Objects.isNull(order.getId())){
            exceptions.add(new OrderIdIsNullException(Orders.class.getName(), OrderErrorCode.ORDER_ID_IS_NULL, "Order Id can not be null"));
        }
        if (Objects.isNull(order.getCustomerId()) || order.getCustomerId().isEmpty()){
            exceptions.add(new CustomerIdIsNullException(Orders.class.getName(), OrderErrorCode.CUSTOMER_ID_IS_NULL, "Customer Id can not be null"));
        }
        if (CollectionUtils.isEmpty(order.getOrderItems()) || !itemPredicate(order.getOrderItems())) {
            exceptions.add(new OrderItemEmptyException(Orders.class.getName(), OrderErrorCode.ORDER_ITEMS_ARE_EMPTY_OR_NULL, "OrderItem can not be empty or null"));
        }
        if (!exceptions.isEmpty()) {
            throw new AggregateException(exceptions);
        }
    }

    private static boolean itemPredicate(Set<OrderItem> items){
        return items.stream().anyMatch(Objects::nonNull);
    }
}