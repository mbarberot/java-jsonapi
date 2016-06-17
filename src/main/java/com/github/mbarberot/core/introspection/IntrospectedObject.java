package com.github.mbarberot.core.introspection;

import com.github.mbarberot.configuration.EntityConfigurationField;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.core.converters.Converter;

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
        return get(configuration.getIdField());
    }

    public Map<String, Object> getAttributes() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> attributesMap = newHashMap();
        for (EntityConfigurationField configField : configuration.getAttributeFields()) {
            attributesMap.put(configField.getFieldName(), get(configField));
        }
        return attributesMap;
    }

    private Object get(EntityConfigurationField configField) throws NoSuchFieldException, IllegalAccessException {
        Converter converter = configField.getConverter();
        Field field = klass.getDeclaredField(configField.getFieldName());
        field.setAccessible(true);
        return converter.toJsonApi(field.get(entity));
    }
}
