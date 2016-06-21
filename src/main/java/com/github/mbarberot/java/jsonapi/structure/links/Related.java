package com.github.mbarberot.java.jsonapi.structure.links;

import com.github.mbarberot.java.jsonapi.structure.Meta;

public class Related {
    private String href;
    private Meta meta;

    protected Related() {
    }

    public Related(String href, Meta meta) {
        this.href = href;
        this.meta = meta;
    }

    public Related(String href) {
        this.href = href;
    }

    public Related(Meta meta) {
        this.meta = meta;
    }

    public String getHref() {
        return href;
    }

    public Related setHref(String href) {
        this.href = href;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Related setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Related related = (Related) o;

        if (href != null ? !href.equals(related.href) : related.href != null) return false;
        return meta != null ? meta.equals(related.meta) : related.meta == null;

    }

    @Override
    public int hashCode() {
        int result = href != null ? href.hashCode() : 0;
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }
}
