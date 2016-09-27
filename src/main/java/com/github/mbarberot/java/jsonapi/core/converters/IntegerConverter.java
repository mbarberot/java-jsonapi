package com.github.mbarberot.java.jsonapi.core.converters;

public class IntegerConverter extends DefaultConverter {
    @Override
    public Object toEntity(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return super.toEntity(value);
        }
    }
}
