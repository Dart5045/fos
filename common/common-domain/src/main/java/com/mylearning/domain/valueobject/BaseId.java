package com.mylearning.domain.valueobject;

import java.util.Objects;

public abstract class BaseId<T> {
    private T t;

    protected BaseId(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return t.equals(baseId.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t);
    }
}
