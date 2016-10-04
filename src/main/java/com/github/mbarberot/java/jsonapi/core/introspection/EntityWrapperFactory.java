package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.utils.ConfigurationNotFoundException;

public class EntityWrapperFactory {
    private JsonApiConfiguration configuration;

    public EntityWrapperFactory(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public EntityReader createEntityReader(Object entity) throws ConfigurationNotFoundException {
        return new EntityReader(
                configuration.getEntityConfiguration(entity.getClass()),
                entity
        );
    }

    public EntityWriter<?> createEntityWriter(String type) throws ConfigurationNotFoundException, JsonApiIntrospectionException {
        JsonApiEntityConfiguration entityConfig = configuration.getEntityConfiguration(type);
        try {
            return new EntityWriter<>(entityConfig, entityConfig.getObjectClass().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new JsonApiIntrospectionException(e);
        }
    }
}
