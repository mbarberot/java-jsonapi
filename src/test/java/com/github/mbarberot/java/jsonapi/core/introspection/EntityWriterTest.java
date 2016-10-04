package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static com.github.mbarberot.java.jsonapi.configuration.ConfigurationField.field;
import static com.github.mbarberot.java.jsonapi.configuration.ConfigurationRelationship.relationship;
import static com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration.newEntityConfiguration;
import static com.github.mbarberot.java.jsonapi.core.converters.Converters.dateConverter;
import static com.github.mbarberot.java.jsonapi.test.utils.BookHelper.getBookConfig;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EntityWriterTest {

    // TODO test set relationships

    private EntityWriter<Book> entityWriter;

    @Before
    public void setUp() throws Exception {
        entityWriter = new EntityWriter<>(getBookConfig(), new Book());
    }

    @Test
    public void setId() throws Exception {
        entityWriter.setId("someid");

        Book expected = new Book();
        expected.setId("someid");

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test
    public void setAttributes() throws Exception {
        entityWriter.setAttributes(newHashMap(of(
                "pages", "200",
                "publication", "1454281200000",
                "isbn", "someisbn"
        )));

        Book expected = new Book();
        expected.setPages(200);
        expected.setPublication(new Date(1454281200000L));
        expected.setIsbn("someisbn");

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test
    public void setAttributes_Null() throws Exception {
        entityWriter.setAttributes(null);

        Book expected = new Book();

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test
    public void setAttributes_Empty() throws Exception {
        entityWriter.setAttributes(newHashMap());

        Book expected = new Book();

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test(expected = JsonApiIntrospectionException.class)
    public void setAttributes_NoConfig_I() throws Exception {
        EntityWriter<Book> myEntityWriter = new EntityWriter<>(
                newEntityConfiguration()
                        .entityClass(Book.class)
                        .idField(field("id"))
                        .type("book")
                        .build(),
                new Book()
        );

        myEntityWriter.setAttributes(newHashMap(of(
                "pages", "200",
                "publication", "1454281200000",
                "isbn", "someisbn"
        )));
    }

    @Test(expected = JsonApiIntrospectionException.class)
    public void setAttributes_NoConfig_II() throws Exception {
        EntityWriter<Book> myEntityWriter = new EntityWriter<>(
                newEntityConfiguration()
                        .entityClass(Book.class)
                        .idField(field("id"))
                        .attributeFields(newArrayList(
                                field("isbn"),
                                field("publication").withConverter(dateConverter())
                        ))
                        .type("book")
                        .build(),
                new Book()
        );

        myEntityWriter.setAttributes(newHashMap(of(
                "pages", "200"
        )));
    }

    @Test
    public void setRelationships() throws Exception {
        Author author = new Author();
        author.setId("author-1");
        Book expected = new Book();
        expected.setAuthor(author);

        entityWriter.setRelationships(newHashMap(of("author", author)));

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test
    public void setRelationships_Null() throws Exception {
        Book expected = new Book();
        entityWriter.setRelationships(newHashMap());

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test
    public void setRelationships_Empty() throws Exception {
        Book expected = new Book();
        entityWriter.setRelationships(null);

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test(expected = JsonApiIntrospectionException.class)
    public void setRelationships_NoConfig_I() throws Exception {
        Author author = new Author();
        author.setId("author-1");
        
        EntityWriter<Book> myEntityWriter = new EntityWriter<>(
                newEntityConfiguration()
                        .entityClass(Book.class)
                        .idField(field("id"))
                        .type("book")
                        .build(),
                new Book()
        );
        
        myEntityWriter.setRelationships(newHashMap(of("author", author)));
    }

    @Test(expected = JsonApiIntrospectionException.class)
    public void setRelationships_NoConfig_II() throws Exception {
        Author author = new Author();
        author.setId("author-1");

        EntityWriter<Book> myEntityWriter = new EntityWriter<>(
                newEntityConfiguration()
                        .entityClass(Book.class)
                        .idField(field("id"))
                        .type("book")
                        .relationshipFields(newArrayList(
                                relationship("foo")
                        ))
                        .build(),
                new Book()
        );

        myEntityWriter.setRelationships(newHashMap(of("author", author)));
    }
}