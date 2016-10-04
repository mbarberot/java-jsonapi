package com.github.mbarberot.java.jsonapi.core.introspection;

import com.github.mbarberot.java.jsonapi.configuration.ConfigurationField;
import com.github.mbarberot.java.jsonapi.configuration.ConfigurationPart;
import com.github.mbarberot.java.jsonapi.configuration.ConfigurationRelationship;
import com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration;
import com.github.mbarberot.java.jsonapi.core.converters.Converter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.empty;

public class EntityWriter<T> {
    private final JsonApiEntityConfiguration configuration;
    private final T entity;
    private final Class entityClass;

    public EntityWriter(JsonApiEntityConfiguration configuration, T entity) {
        this.configuration = configuration;
        this.entity = entity;
        this.entityClass = configuration.getEntityClass();
    }

    public T getEntity() {
        return entity;
    }

    public void setId(String id) throws JsonApiIntrospectionException {
        set(configuration.getIdField(), id);
    }

    public void setAttributes(Map<String, String> attributes) throws JsonApiIntrospectionException {
        if (attributes == null || attributes.isEmpty()) {
            return;
        }

        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            Optional<ConfigurationField> fieldConfig = getEntityConfigurationField(configuration.getAttributeFields(), attribute.getKey());

            if (fieldConfig.isPresent()) {
                set(fieldConfig.get(), attribute.getValue());
            } else {
                throw new JsonApiIntrospectionException("Attribute config not found : " + attribute.getKey());
            }
        }
    }

    public void setRelationships(Map<String, Object> relationships) throws JsonApiIntrospectionException {
        if (relationships == null || relationships.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> relationship : relationships.entrySet()) {
            String fieldName = relationship.getKey();
            Object relEntity = relationship.getValue();

            Optional<ConfigurationRelationship> fieldConfig = getEntityConfigurationRelationship(configuration.getRelationshipFields(), fieldName);

            if (fieldConfig.isPresent()) {
                ConfigurationRelationship relConfig = fieldConfig.get();
                setRawValue(relConfig, relEntity);
            } else {
                throw new JsonApiIntrospectionException("Relation configuration not found");
            }
        }
    }

    private Optional<ConfigurationField> getEntityConfigurationField(List<ConfigurationField> attributeFields, String fieldName) {
        return (attributeFields == null) ? empty() :
                attributeFields
                        .stream()
                        .filter(field -> Objects.equals(field.getFieldName(), fieldName))
                        .findFirst();
    }

    private Optional<ConfigurationRelationship> getEntityConfigurationRelationship(List<ConfigurationRelationship> relationshipFields, String fieldName) {
        return (relationshipFields == null) ? empty() : 
                relationshipFields
                .stream()
                .filter(field -> Objects.equals(field.getFieldName(), fieldName))
                .findFirst();
    }

    private void set(ConfigurationField fieldConfig, String value) throws JsonApiIntrospectionException {
        Converter converter = fieldConfig.getConverter();
        setRawValue(fieldConfig, converter.toEntity(value));
    }

    private void setRawValue(ConfigurationPart partConfig, Object value) throws JsonApiIntrospectionException {
        try {
            Field field = entityClass.getDeclaredField(partConfig.getFieldName());
            field.setAccessible(true);
            field.set(entity, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new JsonApiIntrospectionException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityWriter<?> that = (EntityWriter<?>) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
        return entityClass != null ? entityClass.equals(that.entityClass) : that.entityClass == null;

    }

    @Override
    public int hashCode() {
        int result = configuration != null ? configuration.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (entityClass != null ? entityClass.hashCode() : 0);
        return result;
    }
}
