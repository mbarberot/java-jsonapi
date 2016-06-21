package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.resources.Resource;

public class SingleDataDocument extends DataDocument {
    private Resource data;

    @SuppressWarnings("unused")
    protected SingleDataDocument() {
    }

    public SingleDataDocument(Resource data) {
        this.data = data;
    }

    public Resource getData() {
        return data;
    }

    public void setData(Resource data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SingleDataDocument that = (SingleDataDocument) o;

        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
