package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Jsonapi;
import org.junit.Test;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeJsonApiTest extends JacksonTest {
    @Test
    public void serializeJsonApi() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"jsonapi\": {" +
                        "    \"version\": \"1.0\"" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of("jsonapi", new Jsonapi("1.0")))),
                STRICT
        );
    }
}
