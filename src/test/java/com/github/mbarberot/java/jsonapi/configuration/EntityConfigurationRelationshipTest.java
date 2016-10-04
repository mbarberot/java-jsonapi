package com.github.mbarberot.java.jsonapi.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityConfigurationRelationshipTest {
    private ConfigurationRelationship x;
    private ConfigurationRelationship y;
    private ConfigurationRelationship z;

    @Before
    public void setUp() throws Exception {
        x = new ConfigurationRelationship("author", "author");
        y = new ConfigurationRelationship("author", "author");
        z = new ConfigurationRelationship("author", "author");
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
        assertFalse(x.equals(new ConfigurationRelationship("foo", "author")));
        assertFalse(x.equals(new ConfigurationRelationship("author", "foo")));
        assertFalse(x.equals(new ConfigurationRelationship("foo", "foo")));
    }

    @Test
    public void testEquals_isConsistent() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));

        ConfigurationRelationship notX = new ConfigurationRelationship("foo", "foo");
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
        assertNotEquals(x.hashCode(), new ConfigurationRelationship("foo", "author").hashCode());
        assertNotEquals(x.hashCode(), new ConfigurationRelationship("author", "foo").hashCode());
        assertNotEquals(x.hashCode(), new ConfigurationRelationship("foo", "foo").hashCode());
    }
}