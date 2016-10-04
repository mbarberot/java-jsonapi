package com.github.mbarberot.java.jsonapi.configuration.builders;

import com.github.mbarberot.java.jsonapi.configuration.ConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.ConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;

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

    public JsonApiEntityConfigurationBuilder idField(ConfigurationField id) {
        config.setIdField(id);
        return this;
    }

    public JsonApiEntityConfigurationBuilder attributeFields(List<ConfigurationField> attributes) {
        config.setAttributeFields(attributes);
        return this;
    }

    public JsonApiEntityConfigurationBuilder type(String type) {
        config.setType(type);
        return this;
    }

    public JsonApiEntityConfigurationBuilder relationshipFields(List<ConfigurationRelationship> relationships) {
        config.setRelationshipFields(relationships);
        return this;
    }

    public JsonApiEntityConfiguration build() {
        return config;
    }
}
