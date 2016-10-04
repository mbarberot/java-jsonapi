package com.github.mbarberot.java.jsonapi.configuration;

public class ConfigurationRelationship extends ConfigurationPart {
    private String type;

    public ConfigurationRelationship(String fieldName, String type) {
        super(fieldName);
        this.type = type;
    }

    public static ConfigurationRelationship relationship(String name) {
        return new ConfigurationRelationship(name, name);
    }

    public static ConfigurationRelationship relationship(String fieldName, String type) {
        return new ConfigurationRelationship(fieldName, type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ConfigurationRelationship that = (ConfigurationRelationship) o;

        return type != null ? type.equals(that.type) : that.type == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
