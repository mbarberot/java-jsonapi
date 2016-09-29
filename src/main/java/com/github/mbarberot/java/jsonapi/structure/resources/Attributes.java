package com.github.mbarberot.java.jsonapi.structure.resources;

import java.util.HashMap;
import java.util.Map;

public class Attributes extends HashMap<String, String> {
    public Attributes add(String key, String value) {
        super.put(key, value);
        return this;
    }

    public Attributes addAll(Map<String, String> attributes) {
        super.putAll(attributes);
        return this;
    }
}
