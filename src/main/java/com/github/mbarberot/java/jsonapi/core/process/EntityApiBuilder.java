package com.github.mbarberot.java.jsonapi.core.process;

import com.github.mbarberot.java.jsonapi.core.introspection.EntityWrapperFactory;
import com.github.mbarberot.java.jsonapi.core.introspection.EntityWriter;
import com.github.mbarberot.java.jsonapi.core.introspection.JsonApiIntrospectionException;
import com.github.mbarberot.java.jsonapi.structure.document.Document;
import com.github.mbarberot.java.jsonapi.structure.document.SingleDataDocument;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationship;
import com.github.mbarberot.java.jsonapi.structure.resources.Relationships;
import com.github.mbarberot.java.jsonapi.structure.resources.Resource;
import com.github.mbarberot.java.jsonapi.utils.EntityConfigurationNotFoundException;

import java.util.*;

import static java.util.Collections.singletonList;

public class EntityApiBuilder {
    private final EntityWrapperFactory factory;

    public EntityApiBuilder(EntityWrapperFactory factory) {
        this.factory = factory;
    }


    public Collection<Object> process(Document document) throws JsonApiProcessException {
        SingleDataDocument dataDoc = (SingleDataDocument) document;
        try {
            return singletonList(processData(
                    dataDoc.getData(),
                    dataDoc.getIncluded()
            ).getEntity());
        } catch (EntityConfigurationNotFoundException | JsonApiIntrospectionException e) {
            throw new JsonApiProcessException("Failed to process document " + document, e);
        }
    }

    private EntityWriter<?> processData(Resource data, Collection<Resource> included) throws EntityConfigurationNotFoundException, JsonApiIntrospectionException {
        EntityWriter<?> writer = factory.createEntityWriter(data.getType());
        writer.setId(data.getId());
        writer.setAttributes(data.getAttributes());
        writer.setRelationships(processRelationships(data.getRelationships(), included));
        return writer;
    }

    private Map<String, Object> processRelationships(Relationships relationships, Collection<Resource> included) throws EntityConfigurationNotFoundException, JsonApiIntrospectionException {
        if (relationships == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Relationship> relationship : relationships.entrySet()) {
            Resource relData = relationship.getValue().getData();
            if (relData != null) {
                Optional<Resource> includedData = included.stream()
                        .filter(resource ->
                                Objects.equals(resource.getType(), relData.getType())
                                        && Objects.equals(resource.getId(), relData.getId())
                        ).findFirst();

                if (includedData.isPresent()) {
                    result.put(relationship.getKey(), processData(includedData.get(), included).getEntity());
                } else {
                    result.put(relationship.getKey(), processData(relData, included).getEntity());
                }
            }
        }
        return result;
    }

}
