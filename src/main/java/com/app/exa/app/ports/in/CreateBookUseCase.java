package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;

public interface CreateBookUseCase {

    Uni<Book> createBook(Book book,Long categorie_id);
}
