package com.github.mbarberot.java.jsonapi.core.converters;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateConverterTest {
    
    private DateConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new DateConverter();
    }

    @Test
    public void toJsonApi() throws Exception {
        assertEquals("1454281200000", converter.toJsonApi(new Date(1454281200000L)));
    }

    @Test
    public void toEntity() throws Exception {
        assertEquals(new Date(1454281200000L), converter.toEntity("1454281200000"));
    }
}