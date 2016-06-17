package com.github.mbarberot.core.builder;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class JsonApiErrorBuilder extends MapBuilder {
    @Override
    public Map<String, Object> build() {
        throw new RuntimeException("Not implemented"); // TODO impl
    }
}
