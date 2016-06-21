package com.github.mbarberot.java.jsonapi.structure;

import java.util.HashMap;

public class Meta extends HashMap<String, Object> {
    public Meta add(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
