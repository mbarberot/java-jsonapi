package com.github.mbarberot.java.jsonapi.structure;

public class Jsonapi {
    private String version;
    private Meta meta;

    protected Jsonapi() {
    }

    public Jsonapi(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public Jsonapi setVersion(String version) {
        this.version = version;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Jsonapi setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jsonapi jsonapi = (Jsonapi) o;

        if (version != null ? !version.equals(jsonapi.version) : jsonapi.version != null) return false;
        return meta != null ? meta.equals(jsonapi.meta) : jsonapi.meta == null;

    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }
}
