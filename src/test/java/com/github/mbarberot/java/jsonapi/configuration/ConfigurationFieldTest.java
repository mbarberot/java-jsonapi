package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.core.converters.Converter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationFieldTest {

    @Mock
    private Converter converter;
    @Mock
    private Converter differentConverter;

    private ConfigurationField x;
    private ConfigurationField y;
    private ConfigurationField z;

    @Before
    public void setUp() throws Exception {
        x = new ConfigurationField("id", converter);
        y = new ConfigurationField("id", converter);
        z = new ConfigurationField("id", converter);
    }

    @Test
    public void testEquals_ToSelf() throws Exception {
        //noinspection EqualsWithItself
        assertTrue(x.equals(x));
    }

    @Test
    public void testEquals_IncompatibleType() throws Exception {
        //noinspection EqualsBetweenInconvertibleTypes
        assertFalse(x.equals("foo"));
    }

    @Test
    public void testEquals_ToNull() throws Exception {
        //noinspection ObjectEqualsNull
        assertFalse(x.equals(null));
    }

    @Test
    public void testEquals_isSymmetric() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(y.equals(x));
    }

    @Test
    public void testEquals_isTransitive() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(y.equals(z));
        assertTrue(x.equals(z));
    }

    @Test
    public void testEquals_differentAttributes() throws Exception {
        assertFalse(x.equals(new ConfigurationField("id", differentConverter)));
        assertFalse(x.equals(new ConfigurationField("foo", converter)));
        assertFalse(x.equals(new ConfigurationField("foo", differentConverter)));
    }

    @Test
    public void testEquals_isConsistent() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        
        ConfigurationField notX = new ConfigurationField("foo", differentConverter);
        assertFalse(x.equals(notX));
        assertFalse(x.equals(notX));
        assertFalse(x.equals(notX));
    }

    @Test
    public void testHashCode_isConsistent() throws Exception {
        int initialHashCode = x.hashCode();
        assertEquals(initialHashCode, x.hashCode());
        assertEquals(initialHashCode, x.hashCode());
    }

    @Test
    public void testHashCode_equalsObjects() throws Exception {
        assertTrue(x.equals(y));
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    public void testHashCode_notEqualsObjects() throws Exception {
        assertNotEquals(x.hashCode(), new ConfigurationField("id", differentConverter).hashCode());
        assertNotEquals(x.hashCode(), new ConfigurationField("foo", converter).hashCode());
        assertNotEquals(x.hashCode(), new ConfigurationField("foo", differentConverter).hashCode());
    }
}