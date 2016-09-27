package com.github.mbarberot.java.jsonapi.core.converters;

public interface Converter {
    String toJsonApi(Object value);
    
    Object toEntity(String value);
}
