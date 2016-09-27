package com.github.mbarberot.java.jsonapi.core.converters;

public class Converters {

    private Converters() {
    }

    public static Converter defaultConverter() {
        return new DefaultConverter();
    }
    
    public static Converter dateConverter() {
        return new DateConverter();
    }

    public static Converter integerConverter() {
        return new IntegerConverter();
    }
}