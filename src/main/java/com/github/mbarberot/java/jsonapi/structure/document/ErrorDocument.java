package com.github.mbarberot.java.jsonapi.structure.document;

import com.github.mbarberot.java.jsonapi.structure.errors.Errors;

public class ErrorDocument extends Document {
    private Errors errors;

    protected ErrorDocument() {
    }

    public ErrorDocument(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
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
