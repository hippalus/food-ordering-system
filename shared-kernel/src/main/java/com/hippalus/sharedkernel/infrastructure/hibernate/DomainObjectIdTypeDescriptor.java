package com.hippalus.sharedkernel.infrastructure.hibernate;

import com.hippalus.sharedkernel.domain.DomainObjectId;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;

import java.util.Objects;
import java.util.function.Function;

public class DomainObjectIdTypeDescriptor<T extends DomainObjectId> extends AbstractTypeDescriptor<T> {
    private final transient Function<String, T> factory;

    public DomainObjectIdTypeDescriptor(Class<T> type, Function<String, T> factory) {
        super(type);
        this.factory = factory;
    }

    @Override
    public T fromString(String s) {
        return factory.apply(s);
    }

    @Override
    public String toString(T id) {
        return id.toUUID();
    }

    @Override
    public <X> X unwrap(T t, Class<X> aClass, WrapperOptions wrapperOptions) {
        if (Objects.isNull(t)) {
            return null;
        }
        if (aClass.isAssignableFrom(getJavaType())) {
            return aClass.cast(t);
        }
        if (aClass.isAssignableFrom(String.class)) {
            return aClass.cast(toString(t));
        }
        throw this.unknownUnwrap(aClass);
    }

    @Override
    public <X> T wrap(X x, WrapperOptions wrapperOptions) {
        if (Objects.isNull(x)) {
            return null;
        }
        if (getJavaType().isInstance(x)) {
            return getJavaType().cast(x);
        }
        if (x instanceof String) {
            return this.fromString((String) x);
        }
        throw this.unknownUnwrap(x.getClass());
    }
}
