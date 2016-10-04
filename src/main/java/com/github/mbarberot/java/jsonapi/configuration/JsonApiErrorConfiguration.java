package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.configuration.builders.JsonApiErrorConfigurationBuilder;

public class JsonApiErrorConfiguration extends JsonApiAbstractConfiguration {
    private ConfigurationField statusField;
    private ConfigurationField codeField;
    private ConfigurationField titleField;
    private ConfigurationField detailField;
    private ConfigurationField linksField;
    private ConfigurationField sourceField;
    private ConfigurationField metaField;

    public JsonApiErrorConfiguration() {
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

    public ConfigurationField getStatusField() {
        return statusField;
    }

    public void setStatusField(ConfigurationField statusField) {
        this.statusField = statusField;
    }

    public ConfigurationField getCodeField() {
        return codeField;
    }

    public void setCodeField(ConfigurationField codeField) {
        this.codeField = codeField;
    }

    public ConfigurationField getTitleField() {
        return titleField;
    }

    public void setTitleField(ConfigurationField titleField) {
        this.titleField = titleField;
    }

    public ConfigurationField getDetailField() {
        return detailField;
    }

    public void setDetailField(ConfigurationField detailField) {
        this.detailField = detailField;
    }

    public ConfigurationField getLinksField() {
        return linksField;
    }

    public void setLinksField(ConfigurationField linksField) {
        this.linksField = linksField;
    }

    public ConfigurationField getSourceField() {
        return sourceField;
    }

    public void setSourceField(ConfigurationField sourceField) {
        this.sourceField = sourceField;
    }

    public ConfigurationField getMetaField() {
        return metaField;
    }

    public void setMetaField(ConfigurationField metaField) {
        this.metaField = metaField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JsonApiErrorConfiguration that = (JsonApiErrorConfiguration) o;

        if (statusField != null ? !statusField.equals(that.statusField) : that.statusField != null) return false;
        if (codeField != null ? !codeField.equals(that.codeField) : that.codeField != null) return false;
        if (titleField != null ? !titleField.equals(that.titleField) : that.titleField != null) return false;
        if (detailField != null ? !detailField.equals(that.detailField) : that.detailField != null) return false;
        if (linksField != null ? !linksField.equals(that.linksField) : that.linksField != null) return false;
        if (sourceField != null ? !sourceField.equals(that.sourceField) : that.sourceField != null) return false;
        return metaField != null ? metaField.equals(that.metaField) : that.metaField == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (statusField != null ? statusField.hashCode() : 0);
        result = 31 * result + (codeField != null ? codeField.hashCode() : 0);
        result = 31 * result + (titleField != null ? titleField.hashCode() : 0);
        result = 31 * result + (detailField != null ? detailField.hashCode() : 0);
        result = 31 * result + (linksField != null ? linksField.hashCode() : 0);
        result = 31 * result + (sourceField != null ? sourceField.hashCode() : 0);
        result = 31 * result + (metaField != null ? metaField.hashCode() : 0);
        return result;
    }

    static JsonApiErrorConfigurationBuilder newErrorConfiguration() {
        return new JsonApiErrorConfigurationBuilder();
    }
}
