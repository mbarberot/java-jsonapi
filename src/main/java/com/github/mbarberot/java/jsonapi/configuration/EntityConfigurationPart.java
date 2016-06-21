package com.github.mbarberot.java.jsonapi.configuration;

import lombok.Data;

@Data
public class EntityConfigurationPart {
    protected String fieldName;

    public EntityConfigurationPart(String fieldName) {
        this.fieldName = fieldName;
    }
}
