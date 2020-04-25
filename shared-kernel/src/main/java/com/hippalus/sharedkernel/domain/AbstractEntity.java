package com.hippalus.sharedkernel.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<T extends DomainObjectId> implements DomainObject {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PROTECTED)
    @Id
    T id;

    protected AbstractEntity() {
    }

    public AbstractEntity(@NonNull T id) {
        this.id = id;
    }




}

