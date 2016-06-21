package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.Jsonapi;
import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;
import com.github.mbarberot.java.jsonapi.structure.links.PaginatedLinks;

public class Document {
    private Meta meta;
    private Jsonapi jsonapi;
    private PaginatedLinks links;

    protected Document() {
    }

    public Document setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public Document setJsonapi(Jsonapi jsonapi) {
        this.jsonapi = jsonapi;
        return this;
    }

    public Document setLinks(PaginatedLinks links) {
        this.links = links;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Jsonapi getJsonapi() {
        return jsonapi;
    }

    public PaginatedLinks getLinks() {
        return links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (meta != null ? !meta.equals(document.meta) : document.meta != null) return false;
        if (jsonapi != null ? !jsonapi.equals(document.jsonapi) : document.jsonapi != null) return false;
        return links != null ? links.equals(document.links) : document.links == null;

    }

    @Override
    public int hashCode() {
        int result = meta != null ? meta.hashCode() : 0;
        result = 31 * result + (jsonapi != null ? jsonapi.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }
}
