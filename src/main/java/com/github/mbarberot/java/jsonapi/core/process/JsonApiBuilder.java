package com.github.mbarberot.java.jsonapi.core.process;


import com.github.mbarberot.java.jsonapi.core.introspection.EntityReader;
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

import java.util.*;
import java.util.Map.Entry;

public class JsonApiBuilder implements JsonApiProcess {
    private EntityWrapperFactory factory;

    public JsonApiBuilder(EntityWrapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public DataDocument processOne(Object entity) throws JsonApiProcessException {
        return new SingleDataDocument(processEntity(entity))
                .setIncluded(processIncluded(entity));
    }

    @Override
    public DataDocument processMultiple(Collection<?> entities) throws JsonApiProcessException {
        List<Resource> resources = new ArrayList<>();
        Set<Resource> included = new HashSet<>();
        for (Object entity : entities) {
            resources.add(processEntity(entity));
            List<Resource> includedResources = processIncluded(entity);
            if (includedResources != null) {
                included.addAll(includedResources);
            }
        }
        return new MultipleDataDocument(resources).setIncluded(included);
    }

    private Resource processEntity(Object entity) throws JsonApiProcessException {
        Resource resource;
        try {
            EntityReader wrapper = factory.createEntityReader(entity);
            resource = new Resource(wrapper.getId(), wrapper.getType())
                    .setAttributes(processAttributes(wrapper.getAttributes()))
                    .setRelationships(processRelationships(wrapper.getRelationships()));
        } catch (JsonApiIntrospectionException | EntityConfigurationNotFoundException e) {
            throw new JsonApiProcessException("Failed to convert entity " + entity, e);
        }
        return resource;
    }

    private Attributes processAttributes(Map<String, String> attributes) throws JsonApiIntrospectionException {
        return attributes != null ? new Attributes().addAll(attributes) : null;
    }

    private Relationships processRelationships(Map<String, Object> relations) throws JsonApiProcessException {
        if (relations == null) {
            return null;
        }
        try {
            Relationships relationships = new Relationships();
            for (Entry<String, Object> relation : relations.entrySet()) {
                EntityReader relatedWrapper = factory.createEntityReader(relation.getValue());
                relationships.add(
                        relation.getKey(),
                        new Relationship(new Resource(
                                relatedWrapper.getId(),
                                relatedWrapper.getType()
                        ))
                );
            }
            return relationships;
        } catch (JsonApiIntrospectionException | EntityConfigurationNotFoundException e) {
            throw new JsonApiProcessException("Failed to process relationships", e);
        }

    }

    private List<Resource> processIncluded(Object entity) throws JsonApiProcessException {
        try {
            EntityReader wrapper = factory.createEntityReader(entity);
            Map<String, Object> relations = wrapper.getRelationships();

            if (relations == null) {
                return null;
            }

            List<Resource> includedResources = new ArrayList<>();
            for (Entry<String, Object> relation : relations.entrySet()) {
                includedResources.add(processEntity(relation.getValue()));
            }

            return includedResources;
        } catch (JsonApiIntrospectionException | EntityConfigurationNotFoundException e) {
            throw new JsonApiProcessException("Failed to process included resources", e);
        }
    }
}
