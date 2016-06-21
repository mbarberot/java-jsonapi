package com.github.mbarberot.java.jsonapi.structure;

public class JsonApi {
    private String version;
    private Meta meta;

    protected JsonApi() {
    }

    public JsonApi(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

        JsonApi jsonApi = (JsonApi) o;

        if (version != null ? !version.equals(jsonApi.version) : jsonApi.version != null) return false;
        return meta != null ? meta.equals(jsonApi.meta) : jsonApi.meta == null;

    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }
}
