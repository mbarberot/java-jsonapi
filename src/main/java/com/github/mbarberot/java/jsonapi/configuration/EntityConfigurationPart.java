package com.github.mbarberot.java.jsonapi.configuration;

public class EntityConfigurationPart {
    protected String fieldName;

    public EntityConfigurationPart() {
    }

    public EntityConfigurationPart(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityConfigurationPart that = (EntityConfigurationPart) o;

        return fieldName != null ? fieldName.equals(that.fieldName) : that.fieldName == null;

    }

    @Override
    public int hashCode() {
        return fieldName != null ? fieldName.hashCode() : 0;
    }
}
