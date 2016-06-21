package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.resources.Included;

public class DataDocument extends Document {
    private Included included;

    protected DataDocument() {
    }

    public Included getIncluded() {
        return included;
    }

    public void setIncluded(Included included) {
        this.included = included;
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
