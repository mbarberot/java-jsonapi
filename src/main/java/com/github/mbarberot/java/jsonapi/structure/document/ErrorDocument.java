package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.errors.Error;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ErrorDocument extends Document {
    private List<Error> errors;

    protected ErrorDocument() {
    }

    public ErrorDocument(List<Error> errors) {
        this.errors = errors;
    }

    public ErrorDocument(Error... errors) {
        this.errors = newArrayList(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public ErrorDocument setErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ErrorDocument that = (ErrorDocument) o;

        return errors != null ? errors.equals(that.errors) : that.errors == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }
}
