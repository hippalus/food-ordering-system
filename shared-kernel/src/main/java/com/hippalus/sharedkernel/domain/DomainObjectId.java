package com.hippalus.sharedkernel.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

public abstract class DomainObjectId implements ValueObject {

    @Getter(AccessLevel.PUBLIC)
    private final String uuid;

    @JsonCreator
    protected DomainObjectId(@NonNull String uuid) {
        this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
    }

    public static <T extends DomainObjectId> T randomId(@NonNull Class<T> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Could not create new instance of %s", idClass), ex);
        }
    }

    @JsonValue
    @NonNull
    public String toUUID() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainObjectId)) return false;

        DomainObjectId domainObjectId = (DomainObjectId) o;

        return getUuid() != null ? getUuid().equals(domainObjectId.getUuid()) : domainObjectId.getUuid() == null;
    }

    @Override
    public int hashCode() {
        return getUuid() != null ? getUuid().hashCode() : 0;
    }

    @Override
    public String toString() {
        return toUUID();
    }
}