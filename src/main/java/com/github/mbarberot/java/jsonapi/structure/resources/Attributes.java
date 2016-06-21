package com.github.mbarberot.java.jsonapi.structure.resources;

import java.util.HashMap;
import java.util.Map;

public class Attributes extends HashMap<String, Object> {
    public Attributes add(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Attributes addAll(Map<String, Object> attributes) {
        super.putAll(attributes);
        return this;
    }
}
