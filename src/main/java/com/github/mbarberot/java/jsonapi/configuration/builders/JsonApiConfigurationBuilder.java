package com.github.mbarberot.java.jsonapi.configuration.builders;

import com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiErrorConfiguration;

import java.util.List;

public class JsonApiConfigurationBuilder {
    private final JsonApiConfiguration config;

    public JsonApiConfigurationBuilder() {
        config = new JsonApiConfiguration();
    }

    public JsonApiConfigurationBuilder entityConfigurations(List<JsonApiEntityConfiguration> entityConfigs) {
        config.setEntityConfigurations(entityConfigs);
        return this;
    }

    public JsonApiConfigurationBuilder errorsConfigurations(List<JsonApiErrorConfiguration> errorConfigs) {
        config.setErrorConfigurations(errorConfigs);
        return this;
    }

    public JsonApiConfiguration build() {
        return config;
    }
}
