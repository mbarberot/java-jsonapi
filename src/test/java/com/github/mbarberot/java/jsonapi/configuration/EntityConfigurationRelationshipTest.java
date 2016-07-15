package com.github.mbarberot.java.jsonapi.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityConfigurationRelationshipTest {
    private EntityConfigurationRelationship x;
    private EntityConfigurationRelationship y;
    private EntityConfigurationRelationship z;

    @Before
    public void setUp() throws Exception {
        x = new EntityConfigurationRelationship("author", "author");
        y = new EntityConfigurationRelationship("author", "author");
        z = new EntityConfigurationRelationship("author", "author");
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
        assertFalse(x.equals(new EntityConfigurationRelationship("foo", "author")));
        assertFalse(x.equals(new EntityConfigurationRelationship("author", "foo")));
        assertFalse(x.equals(new EntityConfigurationRelationship("foo", "foo")));
    }

    @Test
    public void testEquals_isConsistent() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));

        EntityConfigurationRelationship notX = new EntityConfigurationRelationship("foo", "foo");
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
        assertNotEquals(x.hashCode(), new EntityConfigurationRelationship("foo", "author").hashCode());
        assertNotEquals(x.hashCode(), new EntityConfigurationRelationship("author", "foo").hashCode());
        assertNotEquals(x.hashCode(), new EntityConfigurationRelationship("foo", "foo").hashCode());
    }
}