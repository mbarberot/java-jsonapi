package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static com.github.mbarberot.java.jsonapi.test.utils.BookHelper.getBookConfig;
import static com.google.common.collect.ImmutableMap.of;
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
    public void getAttributes() throws Exception {
        // TODO test non string attributes
        entityWriter.setAttributes(newHashMap(of(
//                "pages", "200",
                "publication", "1454281200000",
                "isbn", "someisbn"
        )));

        Book expected = new Book();
//        expected.setPages(200);
        expected.setPublication(new Date(1454281200000L));
        expected.setIsbn("someisbn");

        assertEquals(expected, entityWriter.getEntity());
    }

    @Test
    public void getAttributes_Null() throws Exception {
        entityWriter.setAttributes(null);

        Book expected = new Book();

        assertEquals(expected, entityWriter.getEntity());
    }
}