package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration.newEntityConfiguration;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JsonApiEntityConfigurationTest {

    private JsonApiEntityConfiguration x;
    private JsonApiEntityConfiguration y;
    private JsonApiEntityConfiguration z;

    @Mock
    private ConfigurationField idField;
    @Mock
    private ConfigurationField attributeField;
    @Mock
    private ConfigurationRelationship relationField;

    @Before
    public void setUp() throws Exception {
        x = newEntityConfiguration()
                .entityClass(Book.class)
                .type("book")
                .idField(idField)
                .attributeFields(newArrayList(attributeField))
                .relationshipFields(newArrayList(relationField))
                .build();

        y = newEntityConfiguration()
                .entityClass(Book.class)
                .type("book")
                .idField(idField)
                .attributeFields(newArrayList(attributeField))
                .relationshipFields(newArrayList(relationField))
                .build();

        z = newEntityConfiguration()
                .entityClass(Book.class)
                .type("book")
                .idField(idField)
                .attributeFields(newArrayList(attributeField))
                .relationshipFields(newArrayList(relationField))
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
        assertFalse(x.equals(new JsonApiEntityConfiguration()));
        assertFalse(x.equals(newEntityConfiguration()
                .entityClass(Book.class)
                .type("book")
                .idField(idField)
                .attributeFields(newArrayList(attributeField))
                .build()));
        assertFalse(x.equals(newEntityConfiguration()
                .entityClass(Book.class)
                .type("book")
                .idField(idField)
                .relationshipFields(newArrayList(relationField))
                .build()));
        assertFalse(x.equals(newEntityConfiguration()
                .entityClass(Book.class)
                .type("book")
                .attributeFields(newArrayList(attributeField))
                .relationshipFields(newArrayList(relationField))
                .build()));
        assertFalse(x.equals(newEntityConfiguration()
                .entityClass(Book.class)
                .idField(idField)
                .attributeFields(newArrayList(attributeField))
                .relationshipFields(newArrayList(relationField))
                .build()));
        assertFalse(x.equals(newEntityConfiguration()
                .type("book")
                .idField(idField)
                .attributeFields(newArrayList(attributeField))
                .relationshipFields(newArrayList(relationField))
                .build()));
    }

    @Test
    public void testEquals_isConsistent() throws Exception {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));

        JsonApiEntityConfiguration notX = new JsonApiEntityConfiguration();
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
                newEntityConfiguration()
                        .entityClass(Book.class)
                        .type("book")
                        .attributeFields(newArrayList(attributeField))
                        .relationshipFields(newArrayList(relationField))
                        .build().hashCode()
        );

        assertNotEquals(
                x.hashCode(),
                newEntityConfiguration()
                        .entityClass(Book.class)
                        .idField(idField)
                        .attributeFields(newArrayList(attributeField))
                        .relationshipFields(newArrayList(relationField))
                        .build().hashCode()
        );

        assertNotEquals(
                x.hashCode(),
                newEntityConfiguration()
                        .type("book")
                        .idField(idField)
                        .attributeFields(newArrayList(attributeField))
                        .relationshipFields(newArrayList(relationField))
                        .build().hashCode()
        );
    }

}