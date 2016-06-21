package com.github.mbarberot.java.jsonapi.core;

import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.core.introspection.IntrospectedObject;
import com.github.mbarberot.java.jsonapi.structure.document.Document;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.Attributes;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;

public class JsonApiConverter {
    private final JsonApiConfiguration configuration;

    public JsonApiConverter(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Document convertEntity(Object entity) throws EntityConfigurationNotFoundException, NoSuchFieldException, IllegalAccessException {
        JsonApiEntityConfiguration config = getEntityConfiguration(entity);
        IntrospectedObject reflected = new IntrospectedObject(config, entity);

        return new SingleDataDocument(
                new Resource(reflected.getId(), reflected.getType())
                        .setAttributes(new Attributes().addAll(reflected.getAttributes()))
                        .setRelationships(new Relationships().addAll(convertRelationships(config, reflected)))
        );
    }

    private Map<String, Relationship> convertRelationships(JsonApiEntityConfiguration config, IntrospectedObject reflected) throws EntityConfigurationNotFoundException, NoSuchFieldException, IllegalAccessException {
        Map<String, Relationship> relationships = newHashMap();
        for (EntityConfigurationRelationship relationConfig : config.getRelationshipFields()) {
            Object entity = reflected.getRelationship(relationConfig);
            JsonApiEntityConfiguration entityConfig = getEntityConfiguration(entity);

            IntrospectedObject reflectedRelation = new IntrospectedObject(entityConfig, entity);
            relationships.put(
                    relationConfig.getFieldName(),
                    new Relationship(new Resource(reflectedRelation.getId(), reflectedRelation.getType()))
            );
        }
        return relationships;
    }


    private JsonApiEntityConfiguration getEntityConfiguration(Object entity) throws EntityConfigurationNotFoundException {
        Optional<JsonApiEntityConfiguration> result = configuration.getEntityConfigurations()
                .stream()
                .filter(cfg -> cfg.getEntityClass() == entity.getClass())
                .findFirst();

        if (!result.isPresent()) {
            throw new EntityConfigurationNotFoundException(entity.toString());
        }

        return result.get();
    }
}
