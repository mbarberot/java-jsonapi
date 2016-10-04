package com.github.mbarberot.java.jsonapi.configuration.builders;

import com.github.mbarberot.java.jsonapi.configuration.ConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiErrorConfiguration;

public class JsonApiErrorConfigurationBuilder {

    private final JsonApiErrorConfiguration config;

    public JsonApiErrorConfigurationBuilder() {
        config = new JsonApiErrorConfiguration();
    }

    public JsonApiErrorConfigurationBuilder objectClass(Class entityClass) {
        config.setObjectClass(entityClass);
        return this;
    }

    public JsonApiErrorConfigurationBuilder type(String type) {
        config.setType(type);
        return this;
    }

    public JsonApiErrorConfigurationBuilder idField(ConfigurationField idField) {
        config.setIdField(idField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder statusField(ConfigurationField statusField) {
        config.setStatusField(statusField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder codeField(ConfigurationField codeField) {
        config.setCodeField(codeField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder titleField(ConfigurationField titleField) {
        config.setTitleField(titleField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder detailField(ConfigurationField detailField) {
        config.setDetailField(detailField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder linksField(ConfigurationField linksField) {
        config.setLinksField(linksField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder sourceField(ConfigurationField sourceField) {
        config.setSourceField(sourceField);
        return this;
    }

    public JsonApiErrorConfigurationBuilder metaField(ConfigurationField metaField) {
        config.setMetaField(metaField);
        return this;
    }




    public JsonApiErrorConfiguration build() {
        return config;
    }
}
