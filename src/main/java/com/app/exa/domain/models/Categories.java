package com.app.exa.domain.models;
import java.util.*;
import lombok.Data;

@Data
public class Categories {

    private Long id;
    private String name;

    private List<Book> books;
}
