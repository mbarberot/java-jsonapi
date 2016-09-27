package com.github.mbarberot.java.jsonapi.core.converters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultConverterTest {

    private DefaultConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new DefaultConverter();
    }

    @Test
    public void toJsonApi() throws Exception {
        assertEquals("foo", converter.toJsonApi("foo"));
        assertEquals("15", converter.toJsonApi(15));
        assertEquals("15.0", converter.toJsonApi(15f));
        assertEquals("15", converter.toJsonApi(15L));
        assertEquals("true", converter.toJsonApi(true));
        assertEquals("null", converter.toJsonApi(null));
    }

}