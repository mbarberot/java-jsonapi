package com.github.mbarberot.core.builder;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public abstract class MapBuilder {
    protected Map<String,Object> map;

    public MapBuilder() {
        this.map = newHashMap();
    }

    public abstract Map<String,Object> build();
}
