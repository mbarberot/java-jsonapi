package com.github.mbarberot.core.converters;

public interface Converter<T> {
    String toJsonApi(Object value);
}
