package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Jsonapi;
import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.document.DataDocument;
import com.github.mbarberot.java.jsonapi.structure.document.Document;
import com.github.mbarberot.java.jsonapi.structure.document.MultipleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.links.PaginatedLinks;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.google.common.collect.Lists.newArrayList;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JacksonDataDocumentTest extends JacksonTest {
    @Test
    public void testSingleData() throws Exception {
        String json = "" +
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
                "}";
        
        Document dataDocument = new SingleDataDocument(new Resource("1", "book"))
                .setIncluded(newArrayList(new Resource("1", "author")))
                .setMeta(new Meta().add("copyright", "Copyright 2016 Foo."))
                .setJsonapi(new Jsonapi("1.0"))
                .setLinks(new PaginatedLinks("/api/route/to/book/1"));
        
        JSONAssert.assertEquals(json, jsonify(dataDocument), STRICT);
        Assert.assertEquals(dataDocument, parse(json, SingleDataDocument.class));
    }

    @Test
    public void testMultipleData() throws Exception {
        String json = "" +
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
                "}";
        
        Document dataDocument = new MultipleDataDocument(
                new Resource("1", "book"),
                new Resource("2", "book"),
                new Resource("3", "book"))
                .setIncluded(newArrayList(new Resource("1", "author")))
                .setMeta(new Meta().add("copyright", "Copyright 2016 Foo."))
                .setJsonapi(new Jsonapi("1.0"))
                .setLinks(new PaginatedLinks("/api/route/to/book/1"));

        JSONAssert.assertEquals(json, jsonify(dataDocument), STRICT);
        Assert.assertEquals(dataDocument, parse(json, MultipleDataDocument.class));
    }
}
