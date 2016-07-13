package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;
import com.github.mbarberot.java.jsonapi.structure.resources.Attributes;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JacksonResourceTest extends JacksonTest {
    @Test
    public void testResource() throws Exception {
        String json = "{" +
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
                "  }";
        Resource resource = new Resource("1", "book")
                .setAttributes(new Attributes()
                        .add("pages", "200")
                        .add("edition", "bar"))
                .setRelationships(new Relationships()
                        .add("author", new Relationship(new Resource("1", "user")))
                        .add("editor", new Relationship(new Resource("2", "user"))))
                .setLinks(new Links("/api/route/to/book/1"))
                .setMeta(new Meta().add("foo", "bar"));

        JSONAssert.assertEquals(json, jsonify(resource), STRICT);
        Assert.assertEquals(resource, parse(json, Resource.class));
    }
}
