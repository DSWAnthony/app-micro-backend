package com.app.exa.domain.models;

import lombok.Data;

@Data
public class Book {

    private Long id;
    private String title;
    private String description;
    private int pages;
    private String author;
    private Categories categorie;
    private String img_url;
    private String storage_url;

}
