package com.hippalus.paymentservice.domain.domainevents;

import com.hippalus.sharedkernel.domain.DomainEvent;
import com.hippalus.sharedkernel.domain.DomainObjectId;

import java.util.List;

public interface IDomainEventPublisher {
    void publishEvent(List<DomainEvent<? extends DomainObjectId>> eventList);
}
