package com.app.exa.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Table(name = "book")
@Data @NoArgsConstructor @AllArgsConstructor
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String description;
    private int pages;
    private String author;
    private String img_url;
    private String storage_url;
    @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_id",referencedColumnName = "id")
    private CategoriesEntity categories;

    @OneToMany(mappedBy = "bookEntity",cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL},fetch = FetchType.EAGER)
    private List<FavoriteEntity> favorites;

}
