package com.github.mbarberot.configuration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonApiEntityConfiguration {
    private Class entityClass;
    private String idField;
    private String type;
}
