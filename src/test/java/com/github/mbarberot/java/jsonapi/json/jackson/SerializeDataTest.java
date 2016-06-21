package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Jsonapi;
import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.document.Document;
import com.github.mbarberot.java.jsonapi.structure.document.MultipleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.links.PaginatedLinks;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeDataTest extends JacksonTest {
    @Test
    public void serializeSingleData() throws Exception {
        Document document = new SingleDataDocument(new Resource("1", "book"))
                .setIncluded(new Resource("1", "author"))
                .setMeta(new Meta().add("copyright", "Copyright 2016 Foo."))
                .setJsonapi(new Jsonapi("1.0"))
                .setLinks(new PaginatedLinks("/api/route/to/book/1"));
        System.out.println(mapper.writeValueAsString(document));
        assertEquals(
                "" +
                        "{" +
                        "  \"data\" : {" +
                        "      \"id\" : \"1\"," +
                        "      \"type\" : \"book\"" +
                        "  }," +
                        "  \"jsonapi\": {\n" +
                        "    \"version\": \"1.0\"\n" +
                        "  }," +
                        "  \"included\" : [ {" +
                        "    \"id\" : \"1\"," +
                        "    \"type\" : \"author\"" +
                        "  } ]," +
                        "  \"meta\": {" +
                        "    \"copyright\": \"Copyright 2016 Foo.\"" +
                        "  }," +
                        "  \"links\": {" +
                        "    \"self\": \"/api/route/to/book/1\"" +
                        "  }" +
                        "}",
                mapper.writeValueAsString(document),
                STRICT
        );
    }

    @Test
    public void serializeMultipleData() throws Exception {
        Document document = new MultipleDataDocument(
                new Resource("1", "book"),
                new Resource("2", "book"),
                new Resource("3", "book"))
                .setIncluded(new Resource("1", "author"))
                .setMeta(new Meta().add("copyright", "Copyright 2016 Foo."))
                .setJsonapi(new Jsonapi("1.0"))
                .setLinks(new PaginatedLinks("/api/route/to/book/1"));
        System.out.println(mapper.writeValueAsString(document));
        assertEquals(
                "" +
                        "{" +
                        "  \"data\" : [{" +
                        "    \"id\" : \"1\"," +
                        "    \"type\" : \"book\"" +
                        "  }, {" +
                        "    \"id\" : \"2\"," +
                        "    \"type\" : \"book\"" +
                        "  }, {" +
                        "    \"id\" : \"3\"," +
                        "    \"type\" : \"book\"" +
                        "  }]," +
                        "  \"jsonapi\": {\n" +
                        "    \"version\": \"1.0\"\n" +
                        "  }," +
                        "  \"included\" : [ {" +
                        "    \"id\" : \"1\"," +
                        "    \"type\" : \"author\"" +
                        "  } ]," +
                        "  \"meta\": {" +
                        "    \"copyright\": \"Copyright 2016 Foo.\"" +
                        "  }," +
                        "  \"links\": {" +
                        "    \"self\": \"/api/route/to/book/1\"" +
                        "  }" +
                        "}",
                mapper.writeValueAsString(document),
                STRICT
        );
    }
}
