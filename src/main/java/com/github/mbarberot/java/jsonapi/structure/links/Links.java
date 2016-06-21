package com.github.mbarberot.java.jsonapi.structure.links;

public class Links {
    private String self;
    private Related related;

    protected Links() {
    }

    public Links(String self) {
        this.self = self;
    }

    public Links(Related related) {
        this.related = related;
    }

    public Links(String self, Related related) {
        this.self = self;
        this.related = related;
    }

    public String getSelf() {
        return self;
    }

    public Links setSelf(String self) {
        this.self = self;
        return this;
    }

    public Related getRelated() {
        return related;
    }

    public Links setRelated(Related related) {
        this.related = related;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Links links = (Links) o;

        if (self != null ? !self.equals(links.self) : links.self != null) return false;
        return related != null ? related.equals(links.related) : links.related == null;

    }

    @Override
    public int hashCode() {
        int result = self != null ? self.hashCode() : 0;
        result = 31 * result + (related != null ? related.hashCode() : 0);
        return result;
    }
}
