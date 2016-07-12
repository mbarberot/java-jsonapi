package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationPart;
import com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.core.converters.Converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class IntrospectedObject {
    private final JsonApiEntityConfiguration configuration;
    private final Object entity;
    private final Class klass;

    public IntrospectedObject(JsonApiEntityConfiguration configuration, Object entity) {
        this.configuration = configuration;
        this.entity = entity;
        this.klass = configuration.getEntityClass();
    }

    public String getType() {
        return configuration.getType();
    }

    public String getId() throws IllegalAccessException, NoSuchFieldException {
        return get(configuration.getIdField());
    }

    public Map<String, Object> getAttributes() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> attributesMap = new HashMap<>();
        for (EntityConfigurationField configField : configuration.getAttributeFields()) {
            attributesMap.put(configField.getFieldName(), get(configField));
        }
        return attributesMap;
    }

    private String get(EntityConfigurationField configField) throws NoSuchFieldException, IllegalAccessException {
        Converter converter = configField.getConverter();
        return converter.toJsonApi(getRawValue(configField));
    }

    private Object getRawValue(EntityConfigurationPart configField) throws NoSuchFieldException, IllegalAccessException {
        Field field = klass.getDeclaredField(configField.getFieldName());
        field.setAccessible(true);
        return field.get(entity);
    }

    public Object getRelationship(EntityConfigurationRelationship relationConfig) throws NoSuchFieldException, IllegalAccessException {
        return getRawValue(relationConfig);
    }
}
