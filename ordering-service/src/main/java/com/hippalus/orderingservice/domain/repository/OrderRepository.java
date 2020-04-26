package com.hippalus.orderingservice.domain.repository;

import com.hippalus.orderingservice.domain.models.OrderId;
import com.hippalus.orderingservice.domain.models.Orders;
import com.hippalus.sharedkernel.infrastructure.jpa.IJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends IJpaRepository<Orders, OrderId> {
}
