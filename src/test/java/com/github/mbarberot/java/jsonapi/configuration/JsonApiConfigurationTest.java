package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration.newConfiguration;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class JsonApiConfigurationTest {

    @Mock
    private JsonApiEntityConfiguration configA;

    @Mock
    private JsonApiEntityConfiguration configB;

    @Mock
    private JsonApiEntityConfiguration configC;

    private JsonApiConfiguration x;
    private JsonApiConfiguration y;
    private JsonApiConfiguration z;

    @Before
    public void setUp() throws Exception {
        doReturn(Book.class).when(configA).getEntityClass();
        doReturn("book").when(configA).getType();

        doReturn(Author.class).when(configB).getEntityClass();
        doReturn("author").when(configB).getType();
        
        doReturn(String.class).when(configC).getEntityClass();
        doReturn("string").when(configC).getType();

        x = newConfiguration().entityConfigurations(newArrayList(configA, configB)).build();
        y = newConfiguration().entityConfigurations(newArrayList(configA, configB)).build();
        z = newConfiguration().entityConfigurations(newArrayList(configA, configB)).build();
    }

    @Test
    public void getEntityConfiguration_byClass() throws Exception {
        assertEquals(configA, x.getEntityConfiguration(Book.class));
        assertEquals(configB, x.getEntityConfiguration(Author.class));
    }

    @Test
    public void getEntityConfiguration_byType() throws Exception {
        assertEquals(configA, x.getEntityConfiguration("book"));
        assertEquals(configB, x.getEntityConfiguration("author"));
    }

    @Test(expected = EntityConfigurationNotFoundException.class)
    public void getEntityConfiguration_byType_notFound() throws Exception {
        x.getEntityConfiguration(Integer.class);
    }

    @Test(expected = EntityConfigurationNotFoundException.class)
    public void getEntityConfiguration_byName_notFound() throws Exception {
        x.getEntityConfiguration("int");
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
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList()).build()));
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList(configA)).build()));
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList(configB)).build()));
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList(configA, configB, configC)).build()));
    }

    @Test
    public void testEquals_isConsistent() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));

        JsonApiConfiguration notX = newConfiguration().entityConfigurations(newArrayList()).build();
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
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList()).build().hashCode());
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList(configA)).build().hashCode());
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList(configB)).build().hashCode());
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList(configA, configB, configC)).build().hashCode());
    }

}