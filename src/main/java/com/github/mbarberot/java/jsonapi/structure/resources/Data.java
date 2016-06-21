package com.github.mbarberot.java.jsonapi.structure.resources;

public class Data {
    private Resource data;

    public Data(Resource data) {
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

        Data data1 = (Data) o;

        return data != null ? data.equals(data1.data) : data1.data == null;

    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
