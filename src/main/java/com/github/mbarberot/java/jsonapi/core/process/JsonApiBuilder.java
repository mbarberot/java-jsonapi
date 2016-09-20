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

    public JsonApiBuilder(EntityWrapperFactory factory) throws EntityConfigurationNotFoundException {
        this.factory = factory;
    }

    @Override
    public DataDocument processOne(Object entity) throws JsonApiIntrospectionException, EntityConfigurationNotFoundException {
        EntityWrapper wrapper = factory.createEntityWrapper(entity);
        return new SingleDataDocument(
                new Resource(
                        wrapper.getId(),
                        wrapper.getType()
                ).setAttributes(new Attributes().addAll(
                        wrapper.getAttributes()
                )).setRelationships(
                        processRelationships(wrapper)
                )
        );
    }

    @Override
    public DataDocument processMultiple(List<Object> entities) throws JsonApiIntrospectionException, EntityConfigurationNotFoundException {
        List<Resource> resources = new ArrayList<>();
        for (Object entity : entities) {
            EntityWrapper wrapper = factory.createEntityWrapper(entity);
            resources.add(new Resource(
                    wrapper.getId(),
                    wrapper.getType()
            ).setAttributes(new Attributes().addAll(
                    wrapper.getAttributes()
            )).setRelationships(
                    processRelationships(wrapper)
            ));
        }
        return new MultipleDataDocument(resources);
    }

    private Relationships processRelationships(EntityWrapper wrapper) throws JsonApiIntrospectionException, EntityConfigurationNotFoundException {
        Map<String, Object> relations = wrapper.getRelationships();
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
