package com.github.mbarberot.java.jsonapi.core;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.Attributes;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;
import org.junit.Test;

import java.util.Date;

import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship.relationship;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class JsonapiConverterTest {
    @Test
    public void convertSingle() throws Exception {
        Author author = new Author();
        author.setId("someauthorid");
        author.setFirstname("jon");
        author.setLastname("doe");

        Book entity = new Book();
        entity.setId("someid");
        entity.setIsbn("someisbn");
        entity.setPages(200);
        entity.setPublication(new Date(1454281200000L));
        entity.setAuthor(author);

        JsonApiConfiguration config = JsonApiConfiguration.builder()
                .entityConfigurations(newArrayList(
                        JsonApiEntityConfiguration.builder()
                                .entityClass(Book.class)
                                .idField(field("id"))
                                .attributeFields(newArrayList(
                                        field("isbn"),
                                        field("pages"),
                                        field("publication").withConverter(value -> format("%d", ((Date) value).getTime()))
                                ))
                                .relationshipFields(newArrayList(
                                        relationship("author")
                                ))
                                .type("book")
                                .build(),
                        JsonApiEntityConfiguration.builder()
                                .entityClass(Author.class)
                                .idField(field("id"))
                                .attributeFields(newArrayList(
                                        field("firstname"),
                                        field("lastname")
                                ))
                                .type("author")
                                .build()
                ))
                .build();

        assertEquals(
                new SingleDataDocument(
                        new Resource("someid", "book")
                                .setAttributes(
                                        new Attributes()
                                                .add("pages", "200")
                                                .add("isbn", "someisbn")
                                                .add("publication", "1454281200000")
                                )
                                .setRelationships(
                                        new Relationships()
                                                .add("author", new Relationship(new Resource("someauthorid", "author")))

                                )
                ),
                new JsonApiConverter(config).convertEntity(entity)
        );
    }

    @Test(expected = EntityConfigurationNotFoundException.class)
    public void noConfigFound() throws Exception {
        new JsonApiConverter(JsonApiConfiguration.builder().entityConfigurations(newArrayList()).build())
                .convertEntity(new Book());
    }
}