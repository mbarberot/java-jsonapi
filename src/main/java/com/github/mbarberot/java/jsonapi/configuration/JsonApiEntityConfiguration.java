package com.github.mbarberot.java.jsonapi.configuration;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JsonApiEntityConfiguration {
    private Class entityClass;
    private String type;
    private EntityConfigurationField idField;
    private List<EntityConfigurationField> attributeFields;
    private List<EntityConfigurationRelationship> relationshipFields;
}
