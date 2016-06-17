package com.github.mbarberot.core.builder;

import java.util.Map;

public class JsonApiRelationshipsBuilder extends MapBuilder {

    public static JsonApiRelationshipsBuilder relationships() {
        return new JsonApiRelationshipsBuilder();
    }

    public JsonApiRelationshipsBuilder add(String name, JsonApiRelationShipBuilder relationshipBuilder) {
        map.put(name, relationshipBuilder.build());
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return map;
    }


}
