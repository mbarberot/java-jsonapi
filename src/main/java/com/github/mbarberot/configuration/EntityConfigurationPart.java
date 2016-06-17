package com.github.mbarberot.configuration;

import lombok.Data;

@Data
public class EntityConfigurationPart {
    protected String fieldName;

    public EntityConfigurationPart(String fieldName) {
        this.fieldName = fieldName;
    }
}
