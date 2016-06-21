package com.github.mbarberot.java.jsonapi.structure.resources;

import java.util.List;

public class Included {
    private List<Resource> resources;

    protected Included() {
    }

    public Included(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Included included = (Included) o;

        return resources != null ? resources.equals(included.resources) : included.resources == null;

    }

    @Override
    public int hashCode() {
        return resources != null ? resources.hashCode() : 0;
    }
}
