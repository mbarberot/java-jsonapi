package com.github.mbarberot.java.jsonapi.core.process;

import com.github.mbarberot.java.jsonapi.core.introspection.JsonApiIntrospectionException;
import com.github.mbarberot.java.jsonapi.structure.document.DataDocument;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

public interface JsonApiProcess {
    DataDocument process() throws JsonApiIntrospectionException, EntityConfigurationNotFoundException;
}
