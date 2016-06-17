package com.github.mbarberot.core;

import com.github.mbarberot.configuration.JsonApiConfiguration;
import com.github.mbarberot.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.core.introspection.IntrospectedObject;
import com.github.mbarberot.utils.EntityConfigurationNotFoundException;

import java.util.Map;
import java.util.Optional;

import static com.github.mbarberot.core.builder.JsonApiAttributesBuilder.attributes;
import static com.github.mbarberot.core.builder.JsonApiBuilder.document;
import static com.github.mbarberot.core.builder.JsonApiDataBuilder.data;

public class JsonApiConverter {
    private final JsonApiConfiguration configuration;

    public JsonApiConverter(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Map<String, Object> convert(Object entity) throws EntityConfigurationNotFoundException, NoSuchFieldException, IllegalAccessException {
        IntrospectedObject reflected = new IntrospectedObject(getEntityConfiguration(entity), entity);

        return document(
                data(reflected.getType(), reflected.getId())
                        .attributes(attributes().addAll(reflected.getAttributes()))
        ).build();
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
