package com.github.mbarberot.java.jsonapi.structure.errors;

public class Source {
    private String pointer;
    private String parameter;

    protected Source() {
    }

    public Source(String pointer) {
        this.pointer = pointer;
    }

    public String getPointer() {
        return pointer;
    }

    public Source setPointer(String pointer) {
        this.pointer = pointer;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public Source setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Source source = (Source) o;

        if (pointer != null ? !pointer.equals(source.pointer) : source.pointer != null) return false;
        return parameter != null ? parameter.equals(source.parameter) : source.parameter == null;

    }

    @Override
    public int hashCode() {
        int result = pointer != null ? pointer.hashCode() : 0;
        result = 31 * result + (parameter != null ? parameter.hashCode() : 0);
        return result;
    }
}
