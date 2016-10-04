package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.ConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.ConfigurationPart;
import com.github.mbarberot.java.jsonapi.configuration.ConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.core.converters.Converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityReader {
    private final JsonApiEntityConfiguration configuration;
    private final Object entity;
    private final Class entityClass;

    public EntityReader(JsonApiEntityConfiguration configuration, Object entity) {
        this.configuration = configuration;
        this.entity = entity;
        this.entityClass = configuration.getEntityClass();
    }

    public String getType() {
        return configuration.getType();
    }

    public String getId() throws JsonApiIntrospectionException {
        return get(configuration.getIdField());
    }

    public Map<String, String> getAttributes() throws JsonApiIntrospectionException {
        List<ConfigurationField> fields = configuration.getAttributeFields();
        if (fields == null) {
            return null;
        }
        Map<String, String> attributesMap = new HashMap<>();
        for (ConfigurationField fieldConfig : fields) {
            attributesMap.put(fieldConfig.getFieldName(), get(fieldConfig));
        }
        return attributesMap;
    }

    public Map<String, Object> getRelationships() throws JsonApiIntrospectionException {
        List<ConfigurationRelationship> fields = configuration.getRelationshipFields();
        if (fields == null) {
            return null;
        }
        
        Map<String, Object> relationships = new HashMap<>();
        for (ConfigurationRelationship relationConfig : fields) {
            relationships.put(
                    relationConfig.getFieldName(),
                    getRawValue(relationConfig)
            );
        }
        return relationships;
    }

    private String get(ConfigurationField fieldConfig) throws JsonApiIntrospectionException {
        Converter converter = fieldConfig.getConverter();
        return converter.toJsonApi(getRawValue(fieldConfig));
    }

    private Object getRawValue(ConfigurationPart partConfig) throws JsonApiIntrospectionException {
        try {
            Field field = entityClass.getDeclaredField(partConfig.getFieldName());
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new JsonApiIntrospectionException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityReader that = (EntityReader) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
        return entityClass != null ? entityClass.equals(that.entityClass) : that.entityClass == null;

    }

    @Override
    public int hashCode() {
        int result = configuration != null ? configuration.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (entityClass != null ? entityClass.hashCode() : 0);
        return result;
    }
}
