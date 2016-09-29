package com.github.mbarberot.java.jsonapi.core.process;

import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapperFactory;
import com.github.mbarberot.java.jsonapi.core.introspection.EntityWriter;
import com.github.mbarberot.java.jsonapi.structure.document.DataDocument;
import com.github.mbarberot.java.jsonapi.structure.document.Document;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.Attributes;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import com.github.mbarberot.java.jsonapi.test.utils.Author;
import com.github.mbarberot.java.jsonapi.test.utils.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship.relationship;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EntityApiBuilderTest {

    @Mock
    private EntityWrapperFactory factory;

    @Mock
    private EntityWriter<Book> bookWriter;
    
    @Mock
    private EntityWriter<Author> authorWriter;

    @Test
    public void process() throws Exception {
        Author author = new Author();
        author.setId("author-id");
        author.setFirstname("jon");
        author.setLastname("doe");
        
        doReturn(bookWriter).when(factory).createEntityWriter("book");
        doReturn(authorWriter).when(factory).createEntityWriter("author");
        doReturn(author).when(authorWriter).getEntity();
        
        Document document = new SingleDataDocument(
                new Resource("book-1", "book")
                        .setAttributes(
                                new Attributes()
                                        .add("isbn", "isbn-123")                                        
                                        .add("publication", "1454281200000")                                        
                                        .add("pages", "200")
                        )
                        .setRelationships(
                                new Relationships()
                                        .add("author", new Relationship(new Resource("author-1", "author")))
                        )
        ).setIncluded(newArrayList(
                new Resource("author-1", "author")
                        .setAttributes(new Attributes()
                                .add("firstname", "jon")
                                .add("lastname", "doe")
                        )
        ));
        
        new EntityApiBuilder(factory).process(document);

        verify(bookWriter, times(1)).setId(eq("book-1"));
        verify(bookWriter, times(1)).setAttributes(eq(newHashMap(of(
                "isbn", "isbn-123",
                "publication", "1454281200000",
                "pages", "200"
        ))));
        verify(bookWriter, times(1)).setRelationships(eq(newHashMap(of(
                "author", author)
        )));
        
        verify(authorWriter, times(1)).setId(eq("author-1"));
        verify(authorWriter, times(1)).setAttributes(eq(newHashMap(of(
                "firstname", "jon",
                "lastname", "doe"
        ))));
        verify(authorWriter, times(1)).setRelationships(eq(null));
    }
}