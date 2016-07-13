package com.github.mbarberot.java.jsonapi.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;

import java.io.IOException;

class JacksonTest {
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    String jsonify(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    Object parse(String json, Class clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
}
