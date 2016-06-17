package com.github.mbarberot.core;

import com.github.mbarberot.configuration.JsonApiConfiguration;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.utils.EntityConfigurationNotFoundException;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static com.github.mbarberot.core.builder.JsonApiBuilder.document;
import static com.github.mbarberot.core.builder.JsonApiDataBuilder.data;

public class JsonApiConverter {
    private final JsonApiConfiguration configuration;

    public JsonApiConverter(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Map<String, Object> convert(Object entity) throws EntityConfigurationNotFoundException, NoSuchFieldException, IllegalAccessException {
        Optional<JsonApiEntityConfiguration> result = configuration.getEntityConfigurations()
                .stream()
                .filter(cfg -> cfg.getEntityClass() == entity.getClass())
                .findFirst();

        if (!result.isPresent()) {
            throw new EntityConfigurationNotFoundException(entity.toString());
        }

        JsonApiEntityConfiguration entityConfiguration = result.get();
        Class entityClass = entity.getClass();

        Field idField = entityClass.getDeclaredField(entityConfiguration.getIdField());
        idField.setAccessible(true);
        Object id = idField.get(entity);
        Object type = entityConfiguration.getType();

        return document(
                data(type, id)
        ).build();

    }
}
