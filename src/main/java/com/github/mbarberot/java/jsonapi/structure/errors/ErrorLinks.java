package com.github.mbarberot.java.jsonapi.structure.errors;

public class ErrorLinks {
    private String about;

    protected ErrorLinks() {
    }

    public ErrorLinks(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorLinks that = (ErrorLinks) o;

        return about != null ? about.equals(that.about) : that.about == null;

    }

    @Override
    public int hashCode() {
        return about != null ? about.hashCode() : 0;
    }
}
