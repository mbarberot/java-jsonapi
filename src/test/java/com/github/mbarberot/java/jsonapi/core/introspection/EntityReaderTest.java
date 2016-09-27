package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship.relationship;
import static com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration.newEntityConfiguration;
import static com.github.mbarberot.java.jsonapi.test.utils.AuthorHelper.newAuthor;
import static com.github.mbarberot.java.jsonapi.test.utils.BookHelper.getBookConfig;
import static com.github.mbarberot.java.jsonapi.test.utils.BookHelper.newBook;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class EntityReaderTest {

    private Book book;
    private Author author;

    @Before
    public void setUp() throws Exception {
        author = newAuthor("someauthorid", "jon", "doe");
        book = newBook("someid", "someisbn", 200, 1454281200000L, author);
    }

    @Test
    public void getId() throws Exception {
        assertEquals("someid", new EntityReader(getBookConfig(), book).getId());
    }

    @Test
    public void getType() throws Exception {
        assertEquals("book", new EntityReader(getBookConfig(), book).getType());
    }

    @Test
    public void getAttributes() throws Exception {
        assertEquals(
                newHashMap(of(
                        "pages", "200",
                        "isbn", "someisbn",
                        "publication", "1454281200000"
                )),
                new EntityReader(getBookConfig(), book).getAttributes());
    }

    @Test
    public void getAttributes_Null() throws Exception {
        assertNull(new EntityReader(getLiteBookConfig(), book).getAttributes());
    }
    
    @Test
    public void getRelationships() throws Exception {
        assertEquals(
                newHashMap(of("author", author)),
                new EntityReader(getBookConfig(), book).getRelationships()
        );
    }

    @Test
    public void getRelationships_Null() throws Exception {
        assertNull(new EntityReader(getLiteBookConfig(), book).getRelationships());
    }


    private JsonApiEntityConfiguration getLiteBookConfig() {
        return newEntityConfiguration()
                .entityClass(Book.class)
                .idField(field("id"))
                .type("book")
                .build();
    }
}