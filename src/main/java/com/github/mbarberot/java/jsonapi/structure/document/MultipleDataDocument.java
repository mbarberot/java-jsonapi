package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.resources.Resource;

import java.util.List;

import static java.util.Arrays.asList;

public class MultipleDataDocument extends DataDocument {
    private List<Resource> data;

    protected MultipleDataDocument() {
    }

    public MultipleDataDocument(List<Resource> data) {
        this.data = data;
    }

    public MultipleDataDocument(Resource... data) {
        this(asList(data));
    }

    public List<Resource> getData() {
        return data;
    }

    public void setData(List<Resource> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MultipleDataDocument that = (MultipleDataDocument) o;

        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
