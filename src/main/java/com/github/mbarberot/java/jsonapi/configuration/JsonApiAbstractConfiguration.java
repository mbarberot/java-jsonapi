package com.github.mbarberot.java.jsonapi.configuration;

abstract class JsonApiAbstractConfiguration {
    protected Class objectClass;
    protected String type;
    protected ConfigurationField idField;

    JsonApiAbstractConfiguration() {
    }

    public Class getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Class objectClass) {
        this.objectClass = objectClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ConfigurationField getIdField() {
        return idField;
    }

    public void setIdField(ConfigurationField idField) {
        this.idField = idField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonApiAbstractConfiguration that = (JsonApiAbstractConfiguration) o;

        if (objectClass != null ? !objectClass.equals(that.objectClass) : that.objectClass != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return idField != null ? idField.equals(that.idField) : that.idField == null;

    }

    @Override
    public int hashCode() {
        int result = objectClass != null ? objectClass.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idField != null ? idField.hashCode() : 0);
        return result;
    }
}
