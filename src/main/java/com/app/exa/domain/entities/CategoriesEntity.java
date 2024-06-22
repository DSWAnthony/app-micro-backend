package com.app.exa.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Table(name = "categories")
@Data @NoArgsConstructor @AllArgsConstructor
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "categories",cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL},fetch = FetchType.EAGER)
    private List<BookEntity> books;
}
