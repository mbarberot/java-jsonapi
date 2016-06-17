package com.github.mbarberot.configuration;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JsonApiEntityConfiguration {
    private Class entityClass;
    private String idField;
    private List<String> attributeFields;
    private String type;
}
