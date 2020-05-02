package com.hippalus.orderingservice.domain.domainevents;

import com.hippalus.sharedkernel.domain.DomainEvent;
import com.hippalus.sharedkernel.domain.DomainObjectId;

import java.util.List;

public interface IDomainEventPublisher {

    void publish(List<DomainEvent<? extends DomainObjectId>> eventList);
}
