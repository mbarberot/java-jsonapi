package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.core.converters.Converter;
import com.github.mbarberot.java.jsonapi.core.converters.Converters;

import static com.github.mbarberot.java.jsonapi.core.converters.Converters.defaultConverter;

public class EntityConfigurationField extends EntityConfigurationPart {
    private Converter converter;

    public static EntityConfigurationField field(String fieldName) {
        return new EntityConfigurationField(fieldName);
    }
    
    public EntityConfigurationField(String fieldName) {
        this(fieldName, defaultConverter());
    }

    public EntityConfigurationField(String fieldName, Converter converter) {
        super(fieldName);
        this.converter = converter;
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
        if (!super.equals(o)) return false;

        EntityConfigurationField that = (EntityConfigurationField) o;

        return converter != null ? converter.equals(that.converter) : that.converter == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (converter != null ? converter.hashCode() : 0);
        return result;
    }
}
