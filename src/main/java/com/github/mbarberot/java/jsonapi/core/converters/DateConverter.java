package com.github.mbarberot.java.jsonapi.core.converters;

import java.util.Date;

public class DateConverter extends DefaultConverter {
    @Override
    public String toJsonApi(Object value) {
        if (value instanceof Date) {
            return "" + ((Date) value).getTime();
        } else {
            return super.toJsonApi(value);
        }
    }

    @Override
    public Object toEntity(String value) {
        try {
            return new Date(Long.valueOf(value));
        } catch (NumberFormatException e) {
            return super.toEntity(value);
        }
    }
}
