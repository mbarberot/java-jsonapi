package com.github.mbarberot.java.jsonapi.core.converters;

public interface Converter<T> {
    String toJsonApi(Object value);
}
