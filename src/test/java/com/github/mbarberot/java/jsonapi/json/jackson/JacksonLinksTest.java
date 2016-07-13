package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;
import com.github.mbarberot.java.jsonapi.structure.links.Related;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JacksonLinksTest extends JacksonTest {
    @Test
    public void testLinks() throws Exception {
        String json = "{" +
                "    \"self\": \"/api/route/to/user/1\"," +
                "    \"related\": {" +
                "      \"href\": \"/api/route/to/user/1/books\"," +
                "      \"meta\": {" +
                "        \"foo\": \"bar\"" +
                "      }" +
                "    }" +
                "  }";
        Links links = new Links(
                "/api/route/to/user/1",
                new Related(
                        "/api/route/to/user/1/books",
                        new Meta().add("foo", "bar")
                ));
        
        JSONAssert.assertEquals(json, jsonify(links), STRICT);
        Assert.assertEquals(links, parse(json, Links.class));
    }

    @Test
    public void testLinks_OnlySelf() throws Exception {
        String json = "{" +
                "    \"self\": \"/api/route/to/user/1\"" +
                "  }";
        
        Links links = new Links("/api/route/to/user/1");
        
        JSONAssert.assertEquals(json, jsonify(links), STRICT);
        Assert.assertEquals(links, parse(json, Links.class));
    }

    @Test
    public void testLinks_OnlyRelated_Href() throws Exception {
        String json = "{" +
                "    \"related\": {" +
                "      \"href\": \"/api/route/to/user/1/books\"" +
                "    }" +
                "  }";
        
        Links links = new Links(new Related("/api/route/to/user/1/books"));

        JSONAssert.assertEquals(json, jsonify(links), STRICT);
        Assert.assertEquals(links, parse(json, Links.class));
    }

    @Test
    public void testLinks_OnlyRelated_Meta() throws Exception {
        String json = "{" +
                "    \"related\": {" +
                "      \"meta\": {" +
                "        \"foo\": \"bar\"" +
                "      }" +
                "    }" +
                "  }";
        
        Links links = new Links(new Related(new Meta().add("foo", "bar")));

        JSONAssert.assertEquals(json, jsonify(links), STRICT);
        Assert.assertEquals(links, parse(json, Links.class));
    }
}
