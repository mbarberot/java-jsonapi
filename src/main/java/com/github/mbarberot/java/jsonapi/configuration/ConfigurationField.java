package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.core.converters.Converter;

import static com.github.mbarberot.java.jsonapi.core.converters.Converters.defaultConverter;

public class ConfigurationField extends ConfigurationPart {
    private Converter converter;

    public static ConfigurationField field(String fieldName) {
        return new ConfigurationField(fieldName);
    }
    
    public ConfigurationField(String fieldName) {
        this(fieldName, defaultConverter());
    }

    public ConfigurationField(String fieldName, Converter converter) {
        super(fieldName);
        this.converter = converter;
    }

    public ConfigurationField withConverter(Converter converter) {
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

        ConfigurationField that = (ConfigurationField) o;

        return converter != null ? converter.equals(that.converter) : that.converter == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (converter != null ? converter.hashCode() : 0);
        return result;
    }
}
