package com.github.mbarberot.java.jsonapi.core.converters;

public class DefaultConverter implements Converter {
    @Override
    public String toJsonApi(Object value) {
        return value.toString();
    }
}
