package com.github.mbarberot.java.jsonapi.configuration;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JsonApiConfiguration {
    List<JsonApiEntityConfiguration> entityConfigurations;
}
