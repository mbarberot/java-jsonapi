package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Jsonapi;
import com.github.mbarberot.java.jsonapi.structure.Meta;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JacksonJsonApiTest extends JacksonTest {
    @Test
    public void testJsonApi() throws Exception {
        String json = "{" +
                "    \"version\": \"1.0\"," +
                "    \"meta\": {" +
                "      \"foo\": \"bar\"" +
                "    }" +
                "  }";
        Jsonapi jsonApi = new Jsonapi("1.0")
                .setMeta(new Meta().add("foo", "bar"));

        JSONAssert.assertEquals(json, jsonify(jsonApi), STRICT);
        Assert.assertEquals(jsonApi, jsonParse(json, Jsonapi.class));
    }
}
