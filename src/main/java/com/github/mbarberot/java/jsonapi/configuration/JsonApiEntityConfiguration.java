package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.configuration.builders.JsonApiEntityConfigurationBuilder;

import java.util.List;

public class JsonApiEntityConfiguration {
    private Class entityClass;
    private String type;
    private EntityConfigurationField idField;
    private List<EntityConfigurationField> attributeFields;
    private List<EntityConfigurationRelationship> relationshipFields;

    public JsonApiEntityConfiguration() {
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EntityConfigurationField getIdField() {
        return idField;
    }

    public void setIdField(EntityConfigurationField idField) {
        this.idField = idField;
    }

    public List<EntityConfigurationField> getAttributeFields() {
        return attributeFields;
    }

    public void setAttributeFields(List<EntityConfigurationField> attributeFields) {
        this.attributeFields = attributeFields;
    }

    public List<EntityConfigurationRelationship> getRelationshipFields() {
        return relationshipFields;
    }

    public void setRelationshipFields(List<EntityConfigurationRelationship> relationshipFields) {
        this.relationshipFields = relationshipFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonApiEntityConfiguration that = (JsonApiEntityConfiguration) o;

        if (entityClass != null ? !entityClass.equals(that.entityClass) : that.entityClass != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (idField != null ? !idField.equals(that.idField) : that.idField != null) return false;
        if (attributeFields != null ? !attributeFields.equals(that.attributeFields) : that.attributeFields != null)
            return false;
        return relationshipFields != null ? relationshipFields.equals(that.relationshipFields) : that.relationshipFields == null;

    }

    @Override
    public int hashCode() {
        int result = entityClass != null ? entityClass.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idField != null ? idField.hashCode() : 0);
        result = 31 * result + (attributeFields != null ? attributeFields.hashCode() : 0);
        result = 31 * result + (relationshipFields != null ? relationshipFields.hashCode() : 0);
        return result;
    }

    public static JsonApiEntityConfigurationBuilder newEntityConfiguration() {
        return new JsonApiEntityConfigurationBuilder();
    }
}
