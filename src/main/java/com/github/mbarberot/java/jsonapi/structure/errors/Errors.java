package com.github.mbarberot.java.jsonapi.structure.errors;

import java.util.List;

public class Errors {
    private List<Error> errors;

    protected Errors() {
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Errors errors1 = (Errors) o;

        return errors != null ? errors.equals(errors1.errors) : errors1.errors == null;

    }

    @Override
    public int hashCode() {
        return errors != null ? errors.hashCode() : 0;
    }
}
