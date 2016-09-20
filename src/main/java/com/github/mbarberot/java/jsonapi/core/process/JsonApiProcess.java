package com.github.mbarberot.java.jsonapi.core.process;

import com.github.mbarberot.java.jsonapi.structure.document.DataDocument;

import java.util.Collection;

interface JsonApiProcess {
    DataDocument processOne(Object entity) throws JsonApiProcessException;

    DataDocument processMultiple(Collection<?> entity) throws JsonApiProcessException;
}
