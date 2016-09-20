package com.github.mbarberot.java.jsonapi.core.process;


import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapper;
import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapperFactory;
import com.github.mbarberot.java.jsonapi.core.introspection.JsonApiIntrospectionException;
import com.github.mbarberot.java.jsonapi.structure.document.DataDocument;
import com.github.mbarberot.java.jsonapi.structure.document.MultipleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.Attributes;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonApiBuilder implements JsonApiProcess {
    private EntityWrapperFactory factory;

    public JsonApiBuilder(EntityWrapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public DataDocument processOne(Object entity) throws JsonApiProcessException {
        return new SingleDataDocument(processEntity(entity));
    }

    @Override
    public DataDocument processMultiple(List<Object> entities) throws JsonApiProcessException {
        List<Resource> resources = new ArrayList<>();
        for (Object entity : entities) {
            resources.add(processEntity(entity));
        }
        return new MultipleDataDocument(resources);
    }

    private Resource processEntity(Object entity) throws JsonApiProcessException {
        Resource resource;
        try {
            EntityWrapper wrapper = factory.createEntityWrapper(entity);
            resource = new Resource(wrapper.getId(), wrapper.getType())
                    .setAttributes(processAttributes(wrapper.getAttributes()))
                    .setRelationships(processRelationships(wrapper.getRelationships()));
        } catch (JsonApiIntrospectionException | EntityConfigurationNotFoundException e) {
            throw new JsonApiProcessException("Failed to convert entity " + entity, e);
        }
        return resource;
    }

    private Attributes processAttributes(Map<String, Object> attributes) throws JsonApiIntrospectionException {
        return attributes != null ? new Attributes().addAll(attributes) : null;
    }

    private Relationships processRelationships(Map<String, Object> relations) throws JsonApiIntrospectionException, EntityConfigurationNotFoundException {
        if (relations == null) {
            return null;
        }

        Relationships relationships = new Relationships();
        for (Entry<String, Object> relation : relations.entrySet()) {
            EntityWrapper relatedWrapper = factory.createEntityWrapper(relation.getValue());
            relationships.add(
                    relation.getKey(),
                    new Relationship(new Resource(
                            relatedWrapper.getId(),
                            relatedWrapper.getType()
                    ))
            );
        }

        return relationships;
    }
}
