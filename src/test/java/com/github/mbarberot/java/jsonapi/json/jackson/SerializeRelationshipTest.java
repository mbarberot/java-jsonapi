package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.PaginatedLinks;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import org.junit.Test;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeRelationshipTest extends JacksonTest {
    @Test
    public void serializeRelationships_fromData() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"relationships\": {" +
                        "    \"author\": {" +
                        "      \"data\": {" +
                        "        \"id\": \"1\"," +
                        "        \"type\": \"user\"" +
                        "      }," +
                        "      \"links\": {" +
                        "        \"self\": \"/api/route/to/user/1\"" +
                        "      }," +
                        "      \"meta\": {" +
                        "        \"foo\": \"bar\"" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of(
                        "relationships", new Relationships()
                                .add("author", new Relationship(new Resource("1", "user"))
                                        .setLinks(new PaginatedLinks("/api/route/to/user/1"))
                                        .setMeta(new Meta().add("foo", "bar")))
                ))),
                STRICT
        );
    }

    @Test
    public void serializeRelationships_fromLinks() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"relationships\": {" +
                        "    \"author\": {" +
                        "      \"data\": {" +
                        "        \"id\": \"1\"," +
                        "        \"type\": \"user\"" +
                        "      }," +
                        "      \"links\": {" +
                        "        \"self\": \"/api/route/to/user/1\"" +
                        "      }," +
                        "      \"meta\": {" +
                        "        \"foo\": \"bar\"" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of(
                        "relationships", new Relationships()
                                .add("author", new Relationship(new PaginatedLinks("/api/route/to/user/1"))
                                        .setData(new Resource("1", "user"))
                                        .setMeta(new Meta().add("foo", "bar")))
                ))),
                STRICT
        );
    }

    @Test
    public void serializeRelationships_fromMeta() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"relationships\": {" +
                        "    \"author\": {" +
                        "      \"data\": {" +
                        "        \"id\": \"1\"," +
                        "        \"type\": \"user\"" +
                        "      }," +
                        "      \"links\": {" +
                        "        \"self\": \"/api/route/to/user/1\"" +
                        "      }," +
                        "      \"meta\": {" +
                        "        \"foo\": \"bar\"" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of(
                        "relationships", new Relationships()
                                .add("author", new Relationship(new Meta().add("foo", "bar"))
                                        .setData(new Resource("1", "user"))
                                        .setLinks(new PaginatedLinks("/api/route/to/user/1")))
                ))),
                STRICT
        );
    }

    @Test
    public void serializeRelationships_fromAll() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"relationships\": {" +
                        "    \"author\": {" +
                        "      \"data\": {" +
                        "        \"id\": \"1\"," +
                        "        \"type\": \"user\"" +
                        "      }," +
                        "      \"links\": {" +
                        "        \"self\": \"/api/route/to/user/1\"" +
                        "      }," +
                        "      \"meta\": {" +
                        "        \"foo\": \"bar\"" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}",
                jsonify(newHashMap(of(
                        "relationships", new Relationships()
                                .add("author", new Relationship(
                                        new Resource("1", "user"),
                                        new PaginatedLinks("/api/route/to/user/1"),
                                        new Meta().add("foo", "bar")))
                ))),
                STRICT
        );
    }
}
