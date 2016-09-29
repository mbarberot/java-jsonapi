package com.github.mbarberot.java.jsonapi.core;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapperFactory;
import com.github.mbarberot.java.jsonapi.core.process.EntityApiBuilder;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiBuilder;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiProcessException;
import com.github.mbarberot.java.jsonapi.structure.document.Document;

import java.util.Collection;

public class JsonApiConverter {
    private final JsonApiConfiguration configuration;

    public JsonApiConverter(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Document convertEntity(Object entity) throws JsonApiProcessException {
        return new JsonApiBuilder(new EntityWrapperFactory(configuration)).processOne(entity);
    }

    public Document convertEntities(Collection<?> entities) throws JsonApiProcessException {
        return new JsonApiBuilder(new EntityWrapperFactory(configuration)).processMultiple(entities);
    }

    public Collection<Object> convertJsonApi(Document document) throws JsonApiProcessException {
        return new EntityApiBuilder(new EntityWrapperFactory(configuration)).process(document);
    }
}
