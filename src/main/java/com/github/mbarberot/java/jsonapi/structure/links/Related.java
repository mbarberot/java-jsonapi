package com.github.mbarberot.java.jsonapi.structure.links;

import com.github.mbarberot.java.jsonapi.structure.Meta;

public class Related {
    private String href;
    private Meta meta;

    protected Related() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
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
