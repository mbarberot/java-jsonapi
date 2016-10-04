package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.mbarberot.java.jsonapi.configuration.JsonApiErrorConfiguration.newErrorConfiguration;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JsonApiErrorConfigurationTest {

    private JsonApiErrorConfiguration x;
    private JsonApiErrorConfiguration y;
    private JsonApiErrorConfiguration z;

    @Mock
    private ConfigurationField idField;
    @Mock
    private ConfigurationField anyField;


    @Before
    public void setUp() throws Exception {
        x = newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .idField(idField)
                .codeField(anyField)
                .detailField(anyField)
                .linksField(anyField)
                .metaField(anyField)
                .sourceField(anyField)
                .statusField(anyField)
                .titleField(anyField)
                .build();

        y = newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .idField(idField)
                .codeField(anyField)
                .detailField(anyField)
                .linksField(anyField)
                .metaField(anyField)
                .sourceField(anyField)
                .statusField(anyField)
                .titleField(anyField)
                .build();

        z = newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .idField(idField)
                .codeField(anyField)
                .detailField(anyField)
                .linksField(anyField)
                .metaField(anyField)
                .sourceField(anyField)
                .statusField(anyField)
                .titleField(anyField)
                .build();
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
        assertFalse(x.equals(new JsonApiErrorConfiguration()));
        assertFalse(x.equals(newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .idField(idField)
                .sourceField(anyField)
                .build()));
        assertFalse(x.equals(newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .idField(idField)
                .titleField(anyField)
                .build()));
        assertFalse(x.equals(newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .statusField(anyField)
                .build()));
        assertFalse(x.equals(newErrorConfiguration()
                .objectClass(Book.class)
                .idField(idField)
                .codeField(anyField)
                .build()));
        assertFalse(x.equals(newErrorConfiguration()
                .objectClass(Book.class)
                .type("book")
                .idField(idField)
                .codeField(anyField)
                .detailField(anyField)
                .linksField(anyField)
                .metaField(anyField)
                .sourceField(anyField)
                .statusField(anyField)
                .titleField(null)
                .build()));
    }

    @Test
    public void testEquals_isConsistent() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));

        JsonApiErrorConfiguration notX = new JsonApiErrorConfiguration();
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
        assertNotEquals(
                x.hashCode(),
                newErrorConfiguration()
                        .objectClass(Book.class)
                        .type("book")
                        .build().hashCode()
        );

        assertNotEquals(
                x.hashCode(),
                newErrorConfiguration()
                        .objectClass(Book.class)
                        .idField(idField)
                        .build().hashCode()
        );

        assertNotEquals(
                x.hashCode(),
                newErrorConfiguration()
                        .type("book")
                        .idField(idField)
                        .metaField(anyField)
                        .build().hashCode()
        );
    }

}