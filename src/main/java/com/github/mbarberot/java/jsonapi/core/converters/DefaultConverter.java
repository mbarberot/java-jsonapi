package com.github.mbarberot.java.jsonapi.core.converters;

public class DefaultConverter implements Converter {
    @Override
    public String toJsonApi(Object value) {
        return value != null ? value.toString() : "null";
    }

    @Override
    public Object toEntity(String value) {
        return value;
    }
}
