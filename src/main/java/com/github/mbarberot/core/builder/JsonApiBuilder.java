package com.github.mbarberot.core.builder;

import lombok.Builder;

import java.util.Map;

@Builder
public class JsonApiBuilder extends MapBuilder {
    private final String key;
    private final MapBuilder topLevelBuilder;

    public static JsonApiBuilder document(JsonApiDataBuilder dataBuilder) {
        return new JsonApiBuilder("data", dataBuilder);
    }

    public static JsonApiBuilder document(JsonApiErrorBuilder errorBuilder) {
        return new JsonApiBuilder("error", errorBuilder);
    }

    public static JsonApiBuilder document(JsonApiMetaBuilder metaBuilder) {
        return new JsonApiBuilder("meta", metaBuilder);
    }

    public JsonApiBuilder(String key, MapBuilder topLevelBuilder) {
        this.key = key;
        this.topLevelBuilder = topLevelBuilder;
    }

    @Override
    public Map<String, Object> build() {
        map.put(key, topLevelBuilder.build());
        return map;
    }
}
