package com.github.mbarberot.core.builder;

import com.mbarberot.jsonapi.builders.data.JSONApiAttributesBuilder;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

public class JsonApiDataBuilder extends MapBuilder {
    private final Object type;
    private final Object id;

    public static JsonApiDataBuilder data(Object type, Object id) {
        return new JsonApiDataBuilder(type, id);
    }

    public JsonApiDataBuilder(Object type, Object id) {
        this.type = type;
        this.id = id;
    }

    public JsonApiDataBuilder attributes(JsonApiAttributesBuilder attributesBuilder) {
        map.put("attributes", attributesBuilder.build());
        return this;
    }

    @Override
    public Map<String, Object> build() {
        map.putAll(of(
                "id", id,
                "type", type
        ));
        return map;
    }
}
