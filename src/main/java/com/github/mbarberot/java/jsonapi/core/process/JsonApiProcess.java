package com.github.mbarberot.java.jsonapi.core.process;

import com.github.mbarberot.java.jsonapi.core.introspection.JsonApiIntrospectionException;
import com.github.mbarberot.java.jsonapi.structure.document.DataDocument;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

import java.util.List;

public interface JsonApiProcess {
    DataDocument processOne(Object entity) throws JsonApiIntrospectionException, EntityConfigurationNotFoundException;
    DataDocument processMultiple(List<Object> entity) throws JsonApiIntrospectionException, EntityConfigurationNotFoundException;
}
