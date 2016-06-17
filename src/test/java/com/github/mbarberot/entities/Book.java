package com.github.mbarberot.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private String id;
    private String isbn;
    private int pages;
}
