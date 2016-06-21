package com.github.mbarberot.java.jsonapi.configuration;

import com.github.mbarberot.java.jsonapi.core.converters.Converter;
import com.github.mbarberot.java.jsonapi.core.converters.Converters;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityConfigurationField extends EntityConfigurationPart {
    private Converter converter;

    public static EntityConfigurationField field(String fieldName) {
        return new EntityConfigurationField(fieldName);
    }

    public EntityConfigurationField(String fieldName) {
        super(fieldName);
        this.converter = Converters.defaultConverter();
    }

    public EntityConfigurationField withConverter(Converter converter) {
        this.converter = converter;
        return this;
    }
}
