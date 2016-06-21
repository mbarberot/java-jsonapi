package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.JsonApi;
import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;

public class Document {
    private Meta meta;
    private JsonApi jsonApi;
    private Links links;

    protected Document() {
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setJsonApi(JsonApi jsonApi) {
        this.jsonApi = jsonApi;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public JsonApi getJsonApi() {
        return jsonApi;
    }

    public Links getLinks() {
        return links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (meta != null ? !meta.equals(document.meta) : document.meta != null) return false;
        if (jsonApi != null ? !jsonApi.equals(document.jsonApi) : document.jsonApi != null) return false;
        return links != null ? links.equals(document.links) : document.links == null;

    }

    @Override
    public int hashCode() {
        int result = meta != null ? meta.hashCode() : 0;
        result = 31 * result + (jsonApi != null ? jsonApi.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }
}
