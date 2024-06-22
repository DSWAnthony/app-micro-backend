package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetAllBooksUseCase;
import com.app.exa.app.ports.out.BookRepositoryPort;
import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllBooksUseCaseImpl implements GetAllBooksUseCase {
    @Inject
    BookRepositoryPort bookRepositoryPort;

    @Override
    public Uni<List<Book>> getAllBooks() {
        return bookRepositoryPort.getAllBooks();
    }
}
