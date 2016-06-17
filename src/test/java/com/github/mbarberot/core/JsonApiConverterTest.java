package com.github.mbarberot.core;

import com.github.mbarberot.configuration.JsonApiConfiguration;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.entities.Book;
import com.github.mbarberot.utils.EntityConfigurationNotFoundException;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.*;

public class JsonApiConverterTest {
    @Test
    public void testConvert() throws Exception {
        Book entity = Book.builder()
                .id("someid")
                .build();

        JsonApiConfiguration config = JsonApiConfiguration.builder()
                .entityConfigurations(newArrayList(
                        JsonApiEntityConfiguration.builder()
                                .entityClass(Book.class)
                                .idField("id")
                                .type("book")
                                .build()
                ))
                .build();

        assertEquals(
                newHashMap(of(
                        "data", newHashMap(of(
                                "id", "someid",
                                "type", "book"
                        ))
                )),
                new JsonApiConverter(config).convert(entity)
        );
    }

    @Test(expected = EntityConfigurationNotFoundException.class)
    public void noConfigFound() throws Exception {
        new JsonApiConverter(JsonApiConfiguration.builder().entityConfigurations(newArrayList()).build())
                .convert(Book.builder().build());
    }
}