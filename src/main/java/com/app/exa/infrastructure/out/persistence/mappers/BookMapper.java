package com.app.exa.infrastructure.out.persistence.mappers;

import com.app.exa.domain.entities.BookEntity;
import com.app.exa.domain.models.Book;

public class BookMapper {

    public static Book entityToDomain(BookEntity bookEntity){
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setDescription(bookEntity.getDescription());
        book.setPages(bookEntity.getPages());
        book.setAuthor(bookEntity.getAuthor());
        book.setImg_url(bookEntity.getImg_url());
        book.setStorage_url(bookEntity.getStorage_url());
        book.setCategorie(CategorieMapper.entityToDomain(bookEntity.getCategories()));
        return book;
    }

    public static BookEntity domainToEntity(Book book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setPages(book.getPages());
        bookEntity.setImg_url(book.getImg_url());
        bookEntity.setStorage_url(book.getStorage_url());
        return bookEntity;
    }
}
