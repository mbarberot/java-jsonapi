package com.github.mbarberot.java.jsonapi.json.jackson;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.document.ErrorDocument;
import com.github.mbarberot.java.jsonapi.structure.errors.Error;
import com.github.mbarberot.java.jsonapi.structure.errors.ErrorLinks;
import com.github.mbarberot.java.jsonapi.structure.errors.Source;
import org.junit.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class SerializeErrorDocumentTest extends JacksonTest {
    @Test
    public void serializeErrorDocument() throws Exception {
        assertEquals(
                "" +
                        "{" +
                        "  \"errors\": [{" +
                        "    \"id\": \"1\"," +
                        "    \"links\": {" +
                        "      \"about\": \"/url/to/some/help\"" +
                        "    }," +
                        "    \"status\": \"404\"," +
                        "    \"code\": \"baboon\"," +
                        "    \"title\": \"Page not found, Sorry ;-)\"," +
                        "    \"detail\": \"This page may not exists\"," +
                        "    \"source\": {" +
                        "      \"pointer\": \"/json/pointer\"," +
                        "      \"parameter\": \"&foo\"" +
                        "    }," +
                        "    \"meta\": {" +
                        "      \"foo\": \"bar\"" +
                        "    }" +
                        "  }, {" +
                        "    \"id\": \"2\"," +
                        "    \"status\": \"400\"" +
                        "  }]" +
                        "}",
                jsonify(new ErrorDocument(
                        new Error("1")
                                .setLinks(new ErrorLinks("/url/to/some/help"))
                                .setStatus("404")
                                .setCode("baboon")
                                .setTitle("Page not found, Sorry ;-)")
                                .setDetail("This page may not exists")
                                .setSource(new Source("/json/pointer").setParameter("&foo"))
                                .setMeta(new Meta().add("foo", "bar")),
                        new Error("2")
                                .setStatus("400")
                )),
                STRICT
        );
    }
}
