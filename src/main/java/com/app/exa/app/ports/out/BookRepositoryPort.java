package com.app.exa.app.ports.out;

import com.app.exa.domain.models.Book;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import java.util.*;

public interface BookRepositoryPort {

    Uni<List<Book>> getAllBooks();
    Uni<Book> createBook(Book book,Long categorie_id);
    Uni<Book> getBookById(Long id);
    Uni<Respon> deleteBookById(Long id);
    Uni<Book> updateBook(Book book,Long categorie_id);
}
