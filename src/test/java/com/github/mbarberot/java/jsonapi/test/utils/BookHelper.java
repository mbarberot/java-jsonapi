package com.github.mbarberot.java.jsonapi.test.utils;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;

import java.util.Date;

import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship.relationship;
import static com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration.newEntityConfiguration;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class BookHelper {
    public static JsonApiEntityConfiguration getBookConfig() {
        return newEntityConfiguration()
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
                .build();
    }

    public static Book newBook(String id, String isbn, int pages, long timestamp, Author author) {
        Book book = new Book();
        book.setId(id);
        book.setIsbn(isbn);
        book.setPages(pages);
        book.setPublication(new Date(timestamp));
        book.setAuthor(author);
        return book;
    }
}
