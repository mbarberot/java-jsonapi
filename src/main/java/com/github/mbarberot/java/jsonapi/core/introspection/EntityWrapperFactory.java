package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

public class EntityWrapperFactory {
    private JsonApiConfiguration configuration;

    public EntityWrapperFactory(JsonApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public EntityWrapper createEntityWrapper(Object entity) throws EntityConfigurationNotFoundException {
        return new EntityWrapper(
                configuration.getEntityConfiguration(entity.getClass()),
                entity
        );
    }
}
