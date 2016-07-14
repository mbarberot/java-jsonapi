package com.github.mbarberot.java.jsonapi.test.utils;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;

import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration.newEntityConfiguration;
import static com.google.common.collect.Lists.newArrayList;

public class AuthorHelper {
    public static JsonApiEntityConfiguration getAuthorConfig() {
        return newEntityConfiguration()
                .entityClass(Author.class)
                .idField(field("id"))
                .attributeFields(newArrayList(
                        field("firstname"),
                        field("lastname")
                ))
                .type("author")
                .build();
    }

    public static Author newAuthor(String id, String firstname, String lastname) {
        Author author = new Author();
        author.setId(id);
        author.setFirstname(firstname);
        author.setLastname(lastname);
        return author;
    }
}
