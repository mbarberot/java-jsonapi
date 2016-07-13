package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.PaginatedLinks;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JacksonRelationshipTest extends JacksonTest {
    @Test
    public void testRelationships_fromData() throws Exception {
        Relationships relationships = new Relationships()
                .add("author", new Relationship(new Resource("1", "user"))
                        .setLinks(new PaginatedLinks("/api/route/to/user/1"))
                        .setMeta(new Meta().add("foo", "bar")));
        String json = "{" +
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
                "  }";
        
        JSONAssert.assertEquals(json, jsonify(relationships), STRICT);
        Assert.assertEquals(relationships, parse(json, Relationships.class));
    }

    @Test
    public void testRelationships_fromLinks() throws Exception {
        Relationships relationships = new Relationships()
                .add("author", new Relationship(new PaginatedLinks("/api/route/to/user/1"))
                        .setData(new Resource("1", "user"))
                        .setMeta(new Meta().add("foo", "bar")));
        String json = "{" +
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
                "  }";
        
        JSONAssert.assertEquals(json, jsonify(relationships), STRICT);
        Assert.assertEquals(relationships, parse(json, Relationships.class));
    }

    @Test
    public void testRelationships_fromMeta() throws Exception {
        String json = "{" +
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
                "  }";
        
        Relationships relationships = new Relationships()
                .add("author", new Relationship(new Meta().add("foo", "bar"))
                        .setData(new Resource("1", "user"))
                        .setLinks(new PaginatedLinks("/api/route/to/user/1")));
        
        JSONAssert.assertEquals(json, jsonify(relationships), STRICT);
        Assert.assertEquals(relationships, parse(json, Relationships.class));
    }

    @Test
    public void testRelationships_fromAll() throws Exception {
        String json = "{" +
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
                "  }";
        
        Relationships relationships = new Relationships()
                .add("author", new Relationship(
                        new Resource("1", "user"),
                        new PaginatedLinks("/api/route/to/user/1"),
                        new Meta().add("foo", "bar")));
        
        JSONAssert.assertEquals(json, jsonify(relationships), STRICT);
        Assert.assertEquals(relationships, parse(json, Relationships.class));
    }
}
