package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;

public interface UpdateBookUseCase {
    Uni<Book> updateBook(Book book,Long categorie_id);
}
