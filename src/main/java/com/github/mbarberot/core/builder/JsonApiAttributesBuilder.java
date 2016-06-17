package com.github.mbarberot.core.builder;

import java.util.Map;

public class JsonApiAttributesBuilder extends MapBuilder {

    public static JsonApiAttributesBuilder attributes() {
        return new JsonApiAttributesBuilder();
    }

    public JsonApiAttributesBuilder add(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public JsonApiAttributesBuilder addAll(Map<String, Object> attributes) {
        map.putAll(attributes);
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return map;
    }
}
