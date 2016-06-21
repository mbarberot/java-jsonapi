package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.resources.Resource;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DataDocument extends Document {
    private List<Resource> included;

    protected DataDocument() {
    }

    public List<Resource> getIncluded() {
        return included;
    }

    public DataDocument setIncluded(List<Resource> included) {
        this.included = included;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DataDocument that = (DataDocument) o;

        return included != null ? included.equals(that.included) : that.included == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (included != null ? included.hashCode() : 0);
        return result;
    }
}
