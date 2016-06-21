package com.github.mbarberot.java.jsonapi.structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.*;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.Assert.assertEquals;

public class PojoTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void authorTest() throws Exception {
        SingleDataDocument jsonApi = new SingleDataDocument(
                new Resource("1", "author")
                        .setAttributes(
                                new Attributes().add("firstname", "jon").add("lastname", "doe")
                        )
                        .setRelationships(
                                new Relationships().add("friend", new Relationship(new Resource("2", "author")))
                        )
        );
        String generatedJson = mapper.writeValueAsString(jsonApi);
        System.out.println(generatedJson);

        JSONAssert.assertEquals(
                "{\"data\" : { \"type\": \"author\", \"id\": \"1\", \"attributes\" :{ \"firstname\": \"jon\", \"lastname\": \"doe\" }, \"relationships\": { \"friend\": { \"data\": { \"id\": \"2\", \"type\": \"author\" }}}}}",
                generatedJson,
                JSONCompareMode.STRICT
        );
    }

    @Test
    public void authorParseTest() throws Exception {
        SingleDataDocument jsonApi = new SingleDataDocument(
                new Resource("1", "author")
                        .setAttributes(
                                new Attributes().add("firstname", "jon").add("lastname", "doe")
                        )
                        .setRelationships(
                                new Relationships().add("friend", new Relationship(new Resource("2", "author")))
                        )
        );

        assertEquals(
                mapper.readValue("{\"data\" : { \"type\": \"author\", \"id\": \"1\", \"attributes\" :{ \"firstname\": \"jon\", \"lastname\": \"doe\" }, \"relationships\": { \"friend\": { \"data\": { \"id\": \"2\", \"type\": \"author\" }}}}}", SingleDataDocument.class),
                jsonApi
        );
    }
}
