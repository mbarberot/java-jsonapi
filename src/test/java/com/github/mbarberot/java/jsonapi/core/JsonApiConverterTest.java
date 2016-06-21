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

public class JsonApiConverterTest {
    @Test
    public void convertSingle() throws Exception {
        Book entity = Book.builder()
                .id("someid")
                .isbn("someisbn")
                .pages(200)
                .publication(new Date(1454281200000L))
                .author(Author.builder()
                        .id("someauthorid")
                        .firstname("jon")
                        .lastname("doe")
                        .build()
                )
                .build();

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
                .convertEntity(Book.builder().build());
    }
}