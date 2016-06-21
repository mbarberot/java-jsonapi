package com.github.mbarberot.java.jsonapi.structure.errors;

import com.github.mbarberot.java.jsonapi.structure.Meta;

public class Error {
    private String id;
    private ErrorLinks links;
    private String status;
    private String code;
    private String title;
    private String detail;
    private Source source;
    private Meta meta;

    protected Error() {
    }

    public Error(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ErrorLinks getLinks() {
        return links;
    }

    public Error setLinks(ErrorLinks links) {
        this.links = links;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Error setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Error setCode(String code) {
        this.code = code;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Error setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public Error setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public Error setSource(Source source) {
        this.source = source;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Error setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Error error = (Error) o;

        if (id != null ? !id.equals(error.id) : error.id != null) return false;
        if (links != null ? !links.equals(error.links) : error.links != null) return false;
        if (status != null ? !status.equals(error.status) : error.status != null) return false;
        if (code != null ? !code.equals(error.code) : error.code != null) return false;
        if (title != null ? !title.equals(error.title) : error.title != null) return false;
        if (detail != null ? !detail.equals(error.detail) : error.detail != null) return false;
        if (source != null ? !source.equals(error.source) : error.source != null) return false;
        return meta != null ? meta.equals(error.meta) : error.meta == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (links != null ? links.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }
}
