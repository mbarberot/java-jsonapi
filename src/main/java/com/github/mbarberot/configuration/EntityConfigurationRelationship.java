package com.github.mbarberot.configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityConfigurationRelationship extends EntityConfigurationPart {
    private String type;

    public static EntityConfigurationRelationship relationship(String name) {
        return new EntityConfigurationRelationship(name, name);
    }

    public static EntityConfigurationRelationship relationship(String fieldName, String type) {
        return new EntityConfigurationRelationship(fieldName, type);
    }

    public EntityConfigurationRelationship(String fieldName, String type) {
        super(fieldName);
        this.type = type;
    }
}
