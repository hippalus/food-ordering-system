package com.hippalus.sharedkernel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@MappedSuperclass
public abstract class AbstractAggregateRoot<T extends DomainObjectId> extends AbstractEntity<T> {

    @Getter(AccessLevel.PUBLIC)
    @Transient
    protected final transient List<DomainEvent<? extends DomainObjectId>> domainEvents=new ArrayList<>();

    protected AbstractAggregateRoot() {
    }

    protected AbstractAggregateRoot(T id) {
        super(id);
    }

    protected void applyEvent(@NonNull DomainEvent<T> event){
        this.domainEvents.add(event);
    }

    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }

    protected Collection<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}