package com.github.mbarberot.core.converters;

public class DefaultConverter implements Converter<String> {
    @Override
    public String toJsonApi(Object value) {
        return value.toString();
    }
}
