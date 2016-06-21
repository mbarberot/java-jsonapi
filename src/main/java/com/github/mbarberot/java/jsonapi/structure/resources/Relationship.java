package com.github.mbarberot.java.jsonapi.structure.resources;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.PaginatedLinks;

public class Relationship {
    private PaginatedLinks links;
    private Resource data;
    private Meta meta;

    protected Relationship() {
    }

    public Relationship(Resource data) {
        this.data = data;
    }

    public Relationship(PaginatedLinks links) {
        this.links = links;
    }

    public Relationship(Meta meta) {
        this.meta = meta;
    }

    public Relationship(Resource data, PaginatedLinks links, Meta meta) {
        this.data = data;
        this.links = links;
        this.meta = meta;
    }

    public PaginatedLinks getLinks() {
        return links;
    }

    public Relationship setLinks(PaginatedLinks links) {
        this.links = links;
        return this;
    }

    public Resource getData() {
        return data;
    }

    public Relationship setData(Resource data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Relationship setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relationship that = (Relationship) o;

        if (links != null ? !links.equals(that.links) : that.links != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return meta != null ? meta.equals(that.meta) : that.meta == null;

    }

    @Override
    public int hashCode() {
        int result = links != null ? links.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }
}
