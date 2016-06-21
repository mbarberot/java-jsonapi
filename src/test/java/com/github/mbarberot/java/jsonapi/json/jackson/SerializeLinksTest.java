package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;
import com.github.mbarberot.java.jsonapi.structure.links.Related;
import org.junit.Test;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeLinksTest extends JacksonTest {
    @Test
    public void serializeLinks() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"links\": {" +
                        "    \"self\": \"/api/route/to/user/1\"," +
                        "    \"related\": {" +
                        "      \"href\": \"/api/route/to/user/1/books\"," +
                        "      \"meta\": {" +
                        "        \"foo\": \"bar\"" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of(
                        "links", new Links(
                                "/api/route/to/user/1",
                                new Related(
                                        "/api/route/to/user/1/books",
                                        new Meta().add("foo", "bar")
                                ))
                ))),
                STRICT
        );
    }

    @Test
    public void serializeLinks_OnlySelf() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"links\": {" +
                        "    \"self\": \"/api/route/to/user/1\"" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of("links", new Links("/api/route/to/user/1")))),
                STRICT
        );
    }

    @Test
    public void serializeLinks_OnlyRelated() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"links\": {" +
                        "    \"related\": {" +
                        "      \"href\": \"/api/route/to/user/1/books\"" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of("links", new Links(new Related("/api/route/to/user/1/books"))))),
                STRICT
        );

        assertEquals(
                "" +
                        "{" +
                        "  \"links\": {" +
                        "    \"related\": {" +
                        "      \"meta\": {" +
                        "        \"foo\": \"bar\"" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of("links", new Links(new Related(new Meta().add("foo", "bar")))))),
                STRICT
        );
    }
}
