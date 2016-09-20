package com.github.mbarberot.java.jsonapi.core;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapperFactory;
import com.github.mbarberot.java.jsonapi.core.introspection.JsonApiIntrospectionException;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiBuilder;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiProcess;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiProcessException;
import com.github.mbarberot.java.jsonapi.structure.document.Document;
import com.github.mbarberot.java.jsonapi.structure.document.MultipleDataDocument;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class JsonApiConverter {
    private final JsonApiConfiguration configuration;

    public JsonApiConverter(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Document convertEntity(Object entity) throws JsonApiProcessException {
        return new JsonApiBuilder(new EntityWrapperFactory(configuration)).processOne(entity);
    }

    public Document convertEntities(Object... entities) throws JsonApiProcessException {
        return new JsonApiBuilder(new EntityWrapperFactory(configuration)).processMultiple(asList(entities));
    }
}
