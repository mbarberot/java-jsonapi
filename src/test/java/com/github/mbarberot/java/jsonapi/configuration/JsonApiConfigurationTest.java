package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration.newConfiguration;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class JsonApiConfigurationTest {

    private JsonApiConfiguration config;

    @Mock
    private JsonApiEntityConfiguration entityConfig1;

    @Mock
    private JsonApiEntityConfiguration entityConfig2;

    @Before
    public void setUp() throws Exception {
        config = newConfiguration().entityConfigurations(newArrayList(
                entityConfig1,
                entityConfig2
        )).build();

        doReturn(Book.class).when(entityConfig1).getEntityClass();
        doReturn("book").when(entityConfig1).getType();

        doReturn(Author.class).when(entityConfig2).getEntityClass();
        doReturn("author").when(entityConfig2).getType();
    }

    @Test
    public void getEntityConfiguration() throws Exception {
        assertEquals(entityConfig1, config.getEntityConfiguration(Book.class));
        assertEquals(entityConfig2, config.getEntityConfiguration(Author.class));
    }

    @Test
    public void getEntityConfiguration1() throws Exception {
        assertEquals(entityConfig1, config.getEntityConfiguration("book"));
        assertEquals(entityConfig2, config.getEntityConfiguration("author"));
    }

}