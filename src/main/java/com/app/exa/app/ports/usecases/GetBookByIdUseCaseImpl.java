package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetBookByIdUseCase;
import com.app.exa.app.ports.out.BookRepositoryPort;
import com.app.exa.domain.models.Book;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetBookByIdUseCaseImpl implements GetBookByIdUseCase {
    @Inject
    BookRepositoryPort bookRepositoryPort;

    @Override
    public Uni<Book> getBookById(Long id) {
        return bookRepositoryPort.getBookById(id);
    }
}
