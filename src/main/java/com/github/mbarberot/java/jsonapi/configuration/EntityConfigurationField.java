package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.core.converters.Converter;
import com.github.mbarberot.java.jsonapi.core.converters.Converters;

public class EntityConfigurationField extends EntityConfigurationPart {
    private Converter converter;

    public EntityConfigurationField(String fieldName) {
        super(fieldName);
        this.converter = Converters.defaultConverter();
    }
    
    public static EntityConfigurationField field(String fieldName) {
        return new EntityConfigurationField(fieldName);
    }

    public EntityConfigurationField withConverter(Converter converter) {
        this.converter = converter;
        return this;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityConfigurationField that = (EntityConfigurationField) o;

        return converter != null ? converter.equals(that.converter) : that.converter == null;

    }

    @Override
    public int hashCode() {
        return converter != null ? converter.hashCode() : 0;
    }
}
