package com.github.mbarberot.java.jsonapi.core.converters;

@FunctionalInterface
public interface Converter {
    String toJsonApi(Object value);
}
