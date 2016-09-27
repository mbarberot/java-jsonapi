package com.github.mbarberot.java.jsonapi.core.converters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerConverterTest {
    
    private Converter converter;

    @Before
    public void setUp() throws Exception {
        converter = new IntegerConverter();
    }

    @Test
    public void toJsonApi() throws Exception {
        assertEquals("15", converter.toJsonApi(15));
        assertEquals("-8", converter.toJsonApi(-8));
    }

    @Test
    public void toEntity() throws Exception {
        assertEquals(15, converter.toEntity("15"));
        assertEquals(-8, converter.toEntity("-8"));
    }

}