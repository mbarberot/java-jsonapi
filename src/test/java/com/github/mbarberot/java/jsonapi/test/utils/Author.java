package com.github.mbarberot.java.jsonapi.test.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private String id;
    private String firstname;
    private String lastname;
}
