package com.github.mbarberot.java.jsonapi.structure.resources;

import java.util.HashMap;
import java.util.Map;

public class Relationships extends HashMap<String, Relationship> {
    public Relationships add(String key, Relationship value) {
        super.put(key, value);
        return this;
    }

    public Relationships addAll(Map<String, Relationship> relationships) {
        super.putAll(relationships);
        return this;
    }
}
