package com.app.exa.infrastructure.out.persistence.mappers;


import com.app.exa.domain.entities.CategoriesEntity;
import com.app.exa.domain.models.Book;
import com.app.exa.domain.models.Categories;

import java.util.*;
import java.util.stream.Collectors;

public class CategorieMapper {

    public static Categories entityToDomain(CategoriesEntity categoriesEntity){
        Categories categorie = new Categories();
        categorie.setId(categoriesEntity.getId());
        categorie.setName(categoriesEntity.getName());
        if (categoriesEntity.getBooks()!=null) {
            List<Book> bookEntities = categoriesEntity.getBooks()
                    .stream()
                    .map(bookEntity1 -> {
                        Book book = new Book();
                        book.setId(bookEntity1.getId());
                        book.setTitle(bookEntity1.getTitle());
                        book.setDescription(bookEntity1.getDescription());
                        book.setAuthor(bookEntity1.getAuthor());
                        book.setPages(bookEntity1.getPages());
                        return book;
                    })
                    .collect(Collectors.toList());

            categorie.setBooks(bookEntities);
        }
        return categorie;
    }

    public static CategoriesEntity domainToEntity(Categories categories) {
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setId(categories.getId());
        categoriesEntity.setName(categories.getName());
        return categoriesEntity;
    }
}
