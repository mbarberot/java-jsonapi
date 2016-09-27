package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationPart;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.core.converters.Converter;

import java.lang.reflect.Field;
import java.util.*;

public class EntityWriter<T> {
    private final JsonApiEntityConfiguration configuration;
    private final T entity;
    private final Class entityClass;

    public EntityWriter(JsonApiEntityConfiguration configuration, T entity) {
        this.configuration = configuration;
        this.entity = entity;
        this.entityClass = configuration.getEntityClass();
    }

    public void setId(String id) throws JsonApiIntrospectionException {
        set(configuration.getIdField(), id);
    }

    public void setAttributes(Map<String, Object> attributes) throws JsonApiIntrospectionException {
        if (attributes == null || attributes.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            Optional<EntityConfigurationField> fieldConfig = configuration.getAttributeFields()
                    .stream()
                    .filter(field -> Objects.equals(field.getFieldName(), attribute.getKey()))
                    .findFirst();
            
            if(fieldConfig.isPresent()){
                set(fieldConfig.get(), attribute.getValue());
            }
        }
    }

    public void setRelationships(Map<String, Object> relationships) throws JsonApiIntrospectionException {
        if (relationships == null || relationships.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> relationship : relationships.entrySet()) {
            Optional<EntityConfigurationRelationship> fieldConfig = configuration.getRelationshipFields()
                    .stream()
                    .filter(field -> Objects.equals(field.getFieldName(), relationship.getKey()))
                    .findFirst();

            if(fieldConfig.isPresent()){
                setRawValue(fieldConfig.get(), relationship.getValue());
            }
        }
    }

    private void set(EntityConfigurationField fieldConfig, Object value) throws JsonApiIntrospectionException {
        setRawValue(fieldConfig, value); // TODO handle converters
    }

    private void setRawValue(EntityConfigurationPart partConfig, Object value) throws JsonApiIntrospectionException {
        try {
            Field field = entityClass.getDeclaredField(partConfig.getFieldName());
            field.setAccessible(true);
            field.set(entity, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new JsonApiIntrospectionException(e);
        }
    }

    public T getEntity() {
        return entity;
    }
}
