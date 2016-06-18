package com.github.mbarberot.core;

import com.github.mbarberot.configuration.JsonApiConfiguration;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.entities.Author;
import com.github.mbarberot.entities.Book;
import com.github.mbarberot.utils.EntityConfigurationNotFoundException;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.github.mbarberot.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.configuration.EntityConfigurationRelationship.relationship;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class JsonApiConverterTest {
    @Test
    public void testConvert() throws Exception {
        Book entity = Book.builder()
                .id("someid")
                .isbn("someisbn")
                .pages(200)
                .publication(new GregorianCalendar(2016, 1, 1).getTime())
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
                newHashMap(of(
                        "data", newHashMap(of(
                                "attributes", newHashMap(of(
                                        "pages", "200",
                                        "isbn", "someisbn",
                                        "publication", "1454281200000"
                                )),
                                "relationships", newHashMap(of(
                                        "author", newHashMap(of(
                                                "data", newHashMap(of(
                                                        "type", "author",
                                                        "id", "someauthorid"
                                                ))
                                        ))
                                )),
                                "id", "someid",
                                "type", "book"
                        ))
                )),
                new JsonApiConverter(config).convertEntity(entity)
        );
    }

    @Test(expected = EntityConfigurationNotFoundException.class)
    public void noConfigFound() throws Exception {
        new JsonApiConverter(JsonApiConfiguration.builder().entityConfigurations(newArrayList()).build())
                .convertEntity(Book.builder().build());
    }
}