package com.github.mbarberot.java.jsonapi.core;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.Attributes;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;
import org.junit.Test;

import static com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration.newConfiguration;
import static com.github.mbarberot.java.jsonapi.test.utils.AuthorHelper.getAuthorConfig;
import static com.github.mbarberot.java.jsonapi.test.utils.AuthorHelper.newAuthor;
import static com.github.mbarberot.java.jsonapi.test.utils.BookHelper.getBookConfig;
import static com.github.mbarberot.java.jsonapi.test.utils.BookHelper.newBook;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class JsonapiConverterTest {
    @Test
    public void convertSingle() throws Exception {
        Author author = newAuthor("someauthorid", "jon", "doe");
        Book entity = newBook("someid", "someisbn", 200, 1454281200000L, author);

        JsonApiConfiguration config = newConfiguration()
                .entityConfigurations(newArrayList(getBookConfig(), getAuthorConfig()))
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
        new JsonApiConverter(
                newConfiguration().entityConfigurations(newArrayList()).build()
        ).convertEntity(new Book());
    }
}