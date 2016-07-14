package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.configuration.builders.JsonApiConfigurationBuilder;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

public class JsonApiConfiguration {
    private List<JsonApiEntityConfiguration> entityConfigurations;

    public List<JsonApiEntityConfiguration> getEntityConfigurations() {
        return entityConfigurations;
    }

    public JsonApiEntityConfiguration getEntityConfiguration(Class clazz) throws EntityConfigurationNotFoundException {
        Optional<JsonApiEntityConfiguration> config = entityConfigurations
                .stream()
                .filter(entityConfiguration -> entityConfiguration.getEntityClass() == clazz)
                .findFirst();

        if (config.isPresent()) {
            return config.get();
        } else {
            throw new EntityConfigurationNotFoundException(
                    format("Entity configuration not found for class %s", clazz.getName())
            );
        }
    }

    public JsonApiEntityConfiguration getEntityConfiguration(String type) throws EntityConfigurationNotFoundException {
        Optional<JsonApiEntityConfiguration> config = entityConfigurations
                .stream()
                .filter(entityConfiguration -> Objects.equals(entityConfiguration.getType(), type))
                .findFirst();

        if (config.isPresent()) {
            return config.get();
        } else {
            throw new EntityConfigurationNotFoundException(
                    format("Entity configuration not found for type %s", type)
            );
        }
    }

    public void setEntityConfigurations(List<JsonApiEntityConfiguration> entityConfigurations) {
        this.entityConfigurations = entityConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonApiConfiguration that = (JsonApiConfiguration) o;

        return entityConfigurations != null ? entityConfigurations.equals(that.entityConfigurations) : that.entityConfigurations == null;

    }

    @Override
    public int hashCode() {
        return entityConfigurations != null ? entityConfigurations.hashCode() : 0;
    }

    public static JsonApiConfigurationBuilder newConfiguration() {
        return new JsonApiConfigurationBuilder();
    }
}
