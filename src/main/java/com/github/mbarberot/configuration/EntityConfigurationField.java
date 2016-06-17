package com.github.mbarberot.configuration;

import com.github.mbarberot.core.converters.Converter;
import com.github.mbarberot.core.converters.Converters;
import lombok.Data;
import lombok.Getter;

@Data
public class EntityConfigurationField {
    private String fieldName;
    private Converter converter;

    public static EntityConfigurationField field(String fieldName) {
        return new EntityConfigurationField(fieldName);
    }

    public EntityConfigurationField(String fieldName) {
        this.fieldName = fieldName;
        this.converter = Converters.defaultConverter();
    }

    public EntityConfigurationField withConverter(Converter converter) {
        this.converter = converter;
        return this;
    }
}
