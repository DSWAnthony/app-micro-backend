package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.CreateBookUseCase;
import com.app.exa.app.ports.out.BookRepositoryPort;
import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateBookUseCaseImpl implements CreateBookUseCase {
    @Inject
    BookRepositoryPort bookRepositoryPort;

    @Override
    public Uni<Book> createBook(Book book,Long categorie_id) {
        return bookRepositoryPort.createBook(book, categorie_id);
    }
}
