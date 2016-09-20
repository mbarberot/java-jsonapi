package com.github.mbarberot.java.jsonapi.core;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapperFactory;
import com.github.mbarberot.java.jsonapi.core.introspection.JsonApiIntrospectionException;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiBuilder;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiProcess;
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

    public Document convertEntity(Object entity) throws EntityConfigurationNotFoundException, JsonApiIntrospectionException {
        EntityWrapperFactory factory = new EntityWrapperFactory(configuration);
        JsonApiProcess visitor = new JsonApiBuilder(factory);
        return visitor.processOne(entity);
    }

    public Document convertEntities(Object... entities) throws EntityConfigurationNotFoundException, JsonApiIntrospectionException {
        EntityWrapperFactory factory = new EntityWrapperFactory(configuration);
        JsonApiProcess visitor = new JsonApiBuilder(factory);
        return visitor.processMultiple(asList(entities));
    }
}
