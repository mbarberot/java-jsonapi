package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JacksonMetaTest extends JacksonTest {
    @Test
    public void testMeta() throws Exception {
        String json = "{" +
                "    \"simpleinfo\" : \"foo\"," +
                "    \"arrayinfo\" : [ " +
                "      \"foo\"," +
                "      \"bar\"" +
                "    ]," +
                "    \"objectinfo\" : {" +
                "      \"prop2\" : \"bar\"," +
                "      \"prop1\" : \"foo\"" +
                "    }" +
                "  }";
        Meta meta = new Meta()
                .add("simpleinfo", "foo")
                .add("arrayinfo", newArrayList("foo", "bar"))
                .add("objectinfo", newHashMap(of(
                        "prop1", "foo",
                        "prop2", "bar"
                )));
        JSONAssert.assertEquals(json, jsonify(meta), STRICT);
        Assert.assertEquals(meta, parse(json, Meta.class));
    }
}
