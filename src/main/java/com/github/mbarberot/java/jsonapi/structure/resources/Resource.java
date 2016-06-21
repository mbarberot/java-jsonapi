package com.github.mbarberot.java.jsonapi.structure.resources;

import com.github.mbarberot.java.jsonapi.structure.Meta;
import com.github.mbarberot.java.jsonapi.structure.links.Links;

public class Resource {
    private String id;
    private String type;
    private Attributes attributes;
    private Relationships relationships;
    private Links links;
    private Meta meta;

    @SuppressWarnings("unused")
    protected Resource() {
    }

    public Resource(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }

    public Resource setAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public Resource setRelationships(Relationships relationships) {
        this.relationships = relationships;
        return this;
    }

    public Resource setLinks(Links links) {
        this.links = links;
        return this;
    }

    public Resource setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;
        if (type != null ? !type.equals(resource.type) : resource.type != null) return false;
        if (attributes != null ? !attributes.equals(resource.attributes) : resource.attributes != null) return false;
        if (relationships != null ? !relationships.equals(resource.relationships) : resource.relationships != null)
            return false;
        if (links != null ? !links.equals(resource.links) : resource.links != null) return false;
        return meta != null ? meta.equals(resource.meta) : resource.meta == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (relationships != null ? relationships.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }
}
