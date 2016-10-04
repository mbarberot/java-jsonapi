package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import com.github.mbarberot.java.jsonapi.test.utils.MyError;
import com.github.mbarberot.java.jsonapi.utils.ConfigurationNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.ExcludeCategories;
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
    private JsonApiEntityConfiguration configEntityA;
    @Mock
    private JsonApiEntityConfiguration configEntityB;
    @Mock
    private JsonApiEntityConfiguration configEntityC;

    @Mock
    private JsonApiErrorConfiguration configErrorA;
    @Mock
    private JsonApiErrorConfiguration configErrorB;
    @Mock
    private JsonApiErrorConfiguration configErrorC;

    private JsonApiConfiguration x;
    private JsonApiConfiguration y;
    private JsonApiConfiguration z;

    @Before
    public void setUp() throws Exception {
        doReturn(Book.class).when(configEntityA).getObjectClass();
        doReturn("book").when(configEntityA).getType();

        doReturn(Author.class).when(configEntityB).getObjectClass();
        doReturn("author").when(configEntityB).getType();
        
        doReturn(String.class).when(configEntityC).getObjectClass();
        doReturn("string").when(configEntityC).getType();

        doReturn(Exception.class).when(configErrorA).getObjectClass();
        doReturn("exception").when(configErrorA).getType();

        doReturn(MyError.class).when(configErrorB).getObjectClass();
        doReturn("error").when(configErrorB).getType();

        doReturn(Object.class).when(configErrorC).getObjectClass();
        doReturn("string").when(configErrorC).getType();


        x = newConfiguration()
                .entityConfigurations(newArrayList(configEntityA, configEntityB))
                .errorsConfigurations(newArrayList(configErrorA, configErrorB))
                .build();
        y = newConfiguration()
                .entityConfigurations(newArrayList(configEntityA, configEntityB))
                .errorsConfigurations(newArrayList(configErrorA, configErrorB))
                .build();
        z = newConfiguration()
                .entityConfigurations(newArrayList(configEntityA, configEntityB))
                .errorsConfigurations(newArrayList(configErrorA, configErrorB))
                .build();
    }

    @Test
    public void getEntityConfiguration_byClass() throws Exception {
        assertEquals(configEntityA, x.getEntityConfiguration(Book.class));
        assertEquals(configEntityB, x.getEntityConfiguration(Author.class));
    }

    @Test
    public void getEntityConfiguration_byType() throws Exception {
        assertEquals(configEntityA, x.getEntityConfiguration("book"));
        assertEquals(configEntityB, x.getEntityConfiguration("author"));
    }

    @Test(expected = ConfigurationNotFoundException.class)
    public void getEntityConfiguration_byType_notFound() throws Exception {
        x.getEntityConfiguration(Integer.class);
    }

    @Test(expected = ConfigurationNotFoundException.class)
    public void getEntityConfiguration_byName_notFound() throws Exception {
        x.getEntityConfiguration("int");
    }
    
    @Test
    public void getErrorConfiguration_byClass() throws Exception {
        assertEquals(configErrorA, x.getErrorConfiguration(Exception.class));
        assertEquals(configErrorB, x.getErrorConfiguration(MyError.class));
    }

    @Test
    public void getErrorConfiguration_byType() throws Exception {
        assertEquals(configErrorA, x.getErrorConfiguration("exception"));
        assertEquals(configErrorB, x.getErrorConfiguration("error"));
    }

    @Test(expected = ConfigurationNotFoundException.class)
    public void getErrorConfiguration_byType_notFound() throws Exception {
        x.getErrorConfiguration(Integer.class);
    }

    @Test(expected = ConfigurationNotFoundException.class)
    public void getErrorConfiguration_byName_notFound() throws Exception {
        x.getErrorConfiguration("int");
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
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList(configEntityA)).build()));
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList(configEntityB)).build()));
        assertFalse(x.equals(newConfiguration().entityConfigurations(newArrayList(configEntityA, configEntityB, configEntityC)).build()));
        assertFalse(x.equals(newConfiguration()
                .entityConfigurations(newArrayList(configEntityA, configEntityB))
                .errorsConfigurations(newArrayList(configErrorA))
                .build()));
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
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList(configEntityA)).build().hashCode());
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList(configEntityB)).build().hashCode());
        assertNotEquals(x.hashCode(), newConfiguration().entityConfigurations(newArrayList(configEntityA, configEntityB, configEntityC)).build().hashCode());
        assertNotEquals(x.hashCode(), newConfiguration()
                .entityConfigurations(newArrayList(configEntityA, configEntityB))
                .errorsConfigurations(newArrayList(configErrorA))
                .build().hashCode());
    }

}