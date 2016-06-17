package com.github.mbarberot.core.introspection;

import com.github.mbarberot.configuration.JsonApiEntityConfiguration;

import java.lang.reflect.Field;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

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

    public Object getId() throws IllegalAccessException, NoSuchFieldException {
        return introspectedGet(configuration.getIdField());
    }

    public Map<String, Object> getAttributes() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> attributesMap = newHashMap();
        for (String fieldName : configuration.getAttributeFields()) {
            attributesMap.put(fieldName, introspectedGet(fieldName));
        }
        return attributesMap;
    }

    private Object introspectedGet(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field idField = klass.getDeclaredField(fieldName);
        idField.setAccessible(true);
        return idField.get(entity);
    }
}
