package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class EntityWrapperFactoryTest {

    @Mock
    private JsonApiConfiguration config;
    
    @Mock
    private JsonApiEntityConfiguration entityConfig;

    private Book entity;
    
    private EntityWrapperFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new EntityWrapperFactory(config);
        entity = new Book();
        doReturn(entityConfig).when(config).getEntityConfiguration(Book.class);
    }

    @Test
    public void createEntityReader() throws Exception {
        assertEquals(
                new EntityReader(entityConfig, entity), 
                factory.createEntityReader(entity)
        );
    }
}