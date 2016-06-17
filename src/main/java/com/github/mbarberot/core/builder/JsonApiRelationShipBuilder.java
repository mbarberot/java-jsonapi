package com.github.mbarberot.core.builder;

import java.util.Map;

public class JsonApiRelationShipBuilder extends MapBuilder {
    private JsonApiDataBuilder dataBuilder;

    public JsonApiRelationShipBuilder(JsonApiDataBuilder dataBuilder) {
        this.dataBuilder = dataBuilder;
    }

    public static JsonApiRelationShipBuilder relationship(JsonApiDataBuilder dataBuilder) {
        return new JsonApiRelationShipBuilder(dataBuilder);
    }

    @Override
    public Map<String, Object> build() {
        map.put("data", dataBuilder.build());
        return map;
    }
}
