package com.github.mbarberot.java.jsonapi.structure.links;

public class PaginatedLinks extends Links {
    private String first;
    private String last;
    private String prev;
    private String next;

    protected PaginatedLinks() {
    }

    public PaginatedLinks(String self) {
        super(self);
    }

    public PaginatedLinks(Related related) {
        super(related);
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PaginatedLinks that = (PaginatedLinks) o;

        if (first != null ? !first.equals(that.first) : that.first != null) return false;
        if (last != null ? !last.equals(that.last) : that.last != null) return false;
        if (prev != null ? !prev.equals(that.prev) : that.prev != null) return false;
        return next != null ? next.equals(that.next) : that.next == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        result = 31 * result + (prev != null ? prev.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }
}
