package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.configuration.builders.JsonApiConfigurationBuilder;
import com.github.mbarberot.java.jsonapi.utils.ConfigurationNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

public class JsonApiConfiguration {
    private List<JsonApiEntityConfiguration> entityConfigurations;
    private List<JsonApiErrorConfiguration> errorConfigurations;

    public List<JsonApiEntityConfiguration> getEntityConfigurations() {
        return entityConfigurations;
    }

    public JsonApiEntityConfiguration getEntityConfiguration(Class clazz) throws ConfigurationNotFoundException {
        return (JsonApiEntityConfiguration) getConfigurationByClass(clazz, entityConfigurations);
    }

    public JsonApiEntityConfiguration getEntityConfiguration(String type) throws ConfigurationNotFoundException {
        return (JsonApiEntityConfiguration) getConfigurationByType(type, entityConfigurations);
    }

    public void setEntityConfigurations(List<JsonApiEntityConfiguration> entityConfigurations) {
        this.entityConfigurations = entityConfigurations;
    }

    public List<JsonApiErrorConfiguration> getErrorConfigurations() {
        return errorConfigurations;
    }

    public JsonApiErrorConfiguration getErrorConfiguration(Class errorClass) throws ConfigurationNotFoundException {
        return (JsonApiErrorConfiguration) getConfigurationByClass(errorClass, errorConfigurations);
    }

    public JsonApiErrorConfiguration getErrorConfiguration(String type) throws ConfigurationNotFoundException {
        return (JsonApiErrorConfiguration) getConfigurationByType(type, errorConfigurations);
    }

    public void setErrorConfigurations(List<JsonApiErrorConfiguration> errorConfigurations) {
        this.errorConfigurations = errorConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonApiConfiguration that = (JsonApiConfiguration) o;

        if (entityConfigurations != null ? !entityConfigurations.equals(that.entityConfigurations) : that.entityConfigurations != null)
            return false;
        return errorConfigurations != null ? errorConfigurations.equals(that.errorConfigurations) : that.errorConfigurations == null;

    }

    @Override
    public int hashCode() {
        int result = entityConfigurations != null ? entityConfigurations.hashCode() : 0;
        result = 31 * result + (errorConfigurations != null ? errorConfigurations.hashCode() : 0);
        return result;
    }

    private JsonApiAbstractConfiguration getConfigurationByClass(Class clazz, Collection<? extends JsonApiAbstractConfiguration> configurations) throws ConfigurationNotFoundException {
        Optional<? extends JsonApiAbstractConfiguration> config = configurations
                .stream()
                .filter(configuration -> configuration.getObjectClass() == clazz)
                .findFirst();

        if (config.isPresent()) {
            return config.get();
        } else {
            throw new ConfigurationNotFoundException(
                    format("Configuration not found for class %s", clazz.getName())
            );
        }
    }

    private JsonApiAbstractConfiguration getConfigurationByType(String type, Collection<? extends JsonApiAbstractConfiguration> configurations) throws ConfigurationNotFoundException {
        Optional<? extends JsonApiAbstractConfiguration> config = configurations
                .stream()
                .filter(configuration -> Objects.equals(configuration.getType(), type))
                .findFirst();

        if (config.isPresent()) {
            return config.get();
        } else {
            throw new ConfigurationNotFoundException(
                    format("Configuration not found for type %s", type)
            );
        }
    }

    public static JsonApiConfigurationBuilder newConfiguration() {
        return new JsonApiConfigurationBuilder();
    }
}
