package com.github.mbarberot.java.jsonapi.configuration.builders;

import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;

import java.util.ArrayList;
import java.util.List;

public class JsonApiEntityConfigurationBuilder {

    private final JsonApiEntityConfiguration config;

    public JsonApiEntityConfigurationBuilder() {
        config = new JsonApiEntityConfiguration();
    }

    public JsonApiEntityConfigurationBuilder entityClass(Class entityClass) {
        config.setEntityClass(entityClass);
        return this;
    }

    public JsonApiEntityConfigurationBuilder idField(EntityConfigurationField id) {
        config.setIdField(id);
        return this;
    }

    public JsonApiEntityConfigurationBuilder attributeFields(List<EntityConfigurationField> attributes) {
        config.setAttributeFields(attributes);
        return this;
    }

    public JsonApiEntityConfigurationBuilder type(String type) {
        config.setType(type);
        return this;
    }

    public JsonApiEntityConfigurationBuilder relationshipFields(List<EntityConfigurationRelationship> relationships) {
        config.setRelationshipFields(relationships);
        return this;
    }

    public JsonApiEntityConfiguration build() {
        return config;
    }
}
