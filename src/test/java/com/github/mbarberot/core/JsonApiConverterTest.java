package com.github.mbarberot.core;

import com.github.mbarberot.configuration.EntityConfigurationField;
import com.github.mbarberot.configuration.JsonApiConfiguration;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.core.converters.Converter;
import com.github.mbarberot.core.converters.Converters;
import com.github.mbarberot.entities.Book;
import com.github.mbarberot.utils.EntityConfigurationNotFoundException;
import com.sun.javafx.scene.layout.region.Margins;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.github.mbarberot.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.core.converters.Converters.*;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.assertEquals;

public class JsonApiConverterTest {
    @Test
    public void testConvert() throws Exception {
        Book entity = Book.builder()
                .id("someid")
                .isbn("someisbn")
                .pages(200)
                .publication(new GregorianCalendar(2016,1,1).getTime())
                .build();

        JsonApiConfiguration config = JsonApiConfiguration.builder()
                .entityConfigurations(newArrayList(
                        JsonApiEntityConfiguration.builder()
                                .entityClass(Book.class)
                                .idField(field("id"))
                                .attributeFields(newArrayList(
                                        field("isbn"),
                                        field("pages"),
                                        field("publication").withConverter(value -> "" + ((Date) value).getTime())
                                ))
                                .type("book")
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