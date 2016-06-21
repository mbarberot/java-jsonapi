package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import org.junit.Test;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeMetaTest extends JacksonTest {
    @Test
    public void serializeMeta() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"meta\": {" +
                        "    \"simpleinfo\" : \"foo\"," +
                        "    \"arrayinfo\" : [ " +
                        "      \"foo\"," +
                        "      \"bar\"" +
                        "    ]," +
                        "    \"objectinfo\" : {" +
                        "      \"prop2\" : \"bar\"," +
                        "      \"prop1\" : \"foo\"" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of("meta", new Meta()
                        .add("simpleinfo", "foo")
                        .add("arrayinfo", newArrayList("foo", "bar"))
                        .add("objectinfo", newHashMap(of(
                                "prop1", "foo",
                                "prop2", "bar"
                        )))))),
                STRICT
        );
    }
}
