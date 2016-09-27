package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationPart;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.core.converters.Converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityReader {
    private final JsonApiEntityConfiguration configuration;
    private final Object entity;
    private final Class klass;

    public EntityReader(JsonApiEntityConfiguration configuration, Object entity) {
        this.configuration = configuration;
        this.entity = entity;
        this.klass = configuration.getEntityClass();
    }

    public String getType() {
        return configuration.getType();
    }

    public String getId() throws JsonApiIntrospectionException {
        return get(configuration.getIdField());
    }

    public Map<String, Object> getAttributes() throws JsonApiIntrospectionException {
        List<EntityConfigurationField> fields = configuration.getAttributeFields();
        if (fields == null) {
            return null;
        }
        Map<String, Object> attributesMap = new HashMap<>();
        for (EntityConfigurationField fieldConfig : fields) {
            attributesMap.put(fieldConfig.getFieldName(), get(fieldConfig));
        }
        return attributesMap;
    }

    public Map<String, Object> getRelationships() throws JsonApiIntrospectionException {
        List<EntityConfigurationRelationship> fields = configuration.getRelationshipFields();
        if (fields == null) {
            return null;
        }
        
        Map<String, Object> relationships = new HashMap<>();
        for (EntityConfigurationRelationship relationConfig : fields) {
            relationships.put(
                    relationConfig.getFieldName(),
                    getRawValue(relationConfig)
            );
        }
        return relationships;
    }

    private String get(EntityConfigurationField fieldConfig) throws JsonApiIntrospectionException {
        Converter converter = fieldConfig.getConverter();
        return converter.toJsonApi(getRawValue(fieldConfig));
    }

    private Object getRawValue(EntityConfigurationPart partConfig) throws JsonApiIntrospectionException {
        try {
            Field field = klass.getDeclaredField(partConfig.getFieldName());
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new JsonApiIntrospectionException(e);
        }
    }
}
