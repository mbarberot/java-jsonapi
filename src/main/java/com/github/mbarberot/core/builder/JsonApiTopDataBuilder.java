package com.github.mbarberot.core.builder;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

public class JsonApiTopDataBuilder {
    public static Map<String, Object> dataDocument() {
        return newHashMap(of("data", newHashMap()));
    }
}
