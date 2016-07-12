package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.configuration.builders.JsonApiConfigurationBuilder;

import java.util.List;

public class JsonApiConfiguration {
    List<JsonApiEntityConfiguration> entityConfigurations;

    public List<JsonApiEntityConfiguration> getEntityConfigurations() {
        return entityConfigurations;
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

    public static JsonApiConfigurationBuilder builder() {
        return new JsonApiConfigurationBuilder();
    }
}
