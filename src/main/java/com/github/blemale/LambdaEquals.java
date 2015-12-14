package com.github.blemale;

import java.util.Objects;
import java.util.function.Function;

import static java.util.Arrays.stream;

public interface LambdaEquals {

    static <T> boolean equals(T thiz, Object object, Function<T, ?>... propertyAccessors) {
        if (thiz == object) {
            return true;
        }
        if (thiz == null || object == null || thiz.getClass() != object.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        final T other = (T) object;
        return !stream(propertyAccessors)
                .anyMatch(accessor -> !Objects.equals(accessor.apply(thiz), accessor.apply(other)));
    }
}
