package com.github.mbarberot.core;

import com.github.mbarberot.configuration.EntityConfigurationRelationship;
import com.github.mbarberot.configuration.JsonApiConfiguration;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.core.builder.JsonApiRelationshipsBuilder;
import com.github.mbarberot.core.introspection.IntrospectedObject;
import com.github.mbarberot.utils.EntityConfigurationNotFoundException;

import java.util.Map;
import java.util.Optional;

import static com.github.mbarberot.core.builder.JsonApiAttributesBuilder.attributes;
import static com.github.mbarberot.core.builder.JsonApiBuilder.document;
import static com.github.mbarberot.core.builder.JsonApiDataBuilder.data;
import static com.github.mbarberot.core.builder.JsonApiRelationShipBuilder.relationship;
import static com.github.mbarberot.core.builder.JsonApiRelationshipsBuilder.*;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class JsonApiConverter {
    private final JsonApiConfiguration configuration;

    public JsonApiConverter(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Map<String, Object> convertEntity(Object entity) throws EntityConfigurationNotFoundException, NoSuchFieldException, IllegalAccessException {
        JsonApiEntityConfiguration config = getEntityConfiguration(entity);
        IntrospectedObject reflected = new IntrospectedObject(config, entity);


        return document(
                data(reflected.getType(), reflected.getId())
                        .attributes(attributes().addAll(reflected.getAttributes()))
                        .relationships(convertRelationships(config, reflected))
        ).build();
    }

    private JsonApiRelationshipsBuilder convertRelationships(JsonApiEntityConfiguration config, IntrospectedObject reflected) throws EntityConfigurationNotFoundException, NoSuchFieldException, IllegalAccessException {
        JsonApiRelationshipsBuilder relationships = relationships();
        for (EntityConfigurationRelationship relationConfig : config.getRelationshipFields()) {
            Object entity = reflected.getRelationship(relationConfig);
            JsonApiEntityConfiguration entityConfig = getEntityConfiguration(entity);

            IntrospectedObject reflectedRelation = new IntrospectedObject(entityConfig, entity);
            relationships.add(
                    relationConfig.getFieldName(),
                    relationship(data(reflectedRelation.getType(), reflectedRelation.getId()))
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
