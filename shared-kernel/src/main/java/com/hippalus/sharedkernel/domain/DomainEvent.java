package com.hippalus.sharedkernel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter(AccessLevel.PUBLIC)
public abstract class DomainEvent<T extends DomainObjectId> implements DomainObject{

    @Setter(AccessLevel.PUBLIC)
    protected  T entityId;
    private final String eventId;
    private final OffsetDateTime occurredDate;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredDate = OffsetDateTime.now();
    }
}
