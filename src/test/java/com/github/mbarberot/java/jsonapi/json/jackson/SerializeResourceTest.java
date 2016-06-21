package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;
import com.github.mbarberot.java.jsonapi.structure.resources.*;
import org.junit.Test;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeResourceTest extends JacksonTest {
    @Test
    public void serializeResource() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"data\": {" +
                        "    \"id\": \"1\"," +
                        "    \"type\": \"book\"," +
                        "    \"attributes\": {" +
                        "      \"pages\": \"200\"," +
                        "      \"edition\": \"bar\"" +
                        "    }," +
                        "    \"relationships\": {" +
                        "      \"author\": {" +
                        "        \"data\": {" +
                        "          \"id\": \"1\"," +
                        "          \"type\": \"user\"" +
                        "        }" +
                        "      }, " +
                        "      \"editor\": {" +
                        "        \"data\": {" +
                        "          \"id\": \"2\"," +
                        "          \"type\": \"user\"" +
                        "        }" +
                        "      }" +
                        "    }," +
                        "    \"links\": {" +
                        "      \"self\": \"/api/route/to/book/1\"" +
                        "    }," +
                        "    \"meta\": {" +
                        "      \"foo\": \"bar\"" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of(
                        "data", new Resource("1", "book")
                                .setAttributes(new Attributes()
                                        .add("pages", "200")
                                        .add("edition", "bar"))
                                .setRelationships(new Relationships()
                                        .add("author", new Relationship(new Resource("1", "user")))
                                        .add("editor", new Relationship(new Resource("2", "user"))))
                                .setLinks(new Links("/api/route/to/book/1"))
                                .setMeta(new Meta().add("foo", "bar"))
                ))),
                STRICT
        );
    }
}
